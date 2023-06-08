package com.daily.controller;

import com.daily.config.R;
import com.daily.domain.User;
import com.daily.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author a1002
 * <p>
 * <p>
 * 在Spring中@RestController的作用等同于@Controller + @ResponseBody
 * 在一个类上添加@Controller注解，表明了这个类是一个控制器类
 * @RequestMapping注解是一个用来处理请求地址映射的注解，可用于映射一个请求或一个方法，可以用在类或方法上
 * @Slf4j 为log日志的功能
 * @RequestParam 主要用于将请求参数区域的数据映射到控制层方法的参数上
 * @PathVariable 映射 URL 绑定的占位符
 * 通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中:URL 中的 {xxx} 占位符可以通过
 * @PathVariable(“xxx”) 绑定到操作方法的入参中。
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    //自动装配RedisTemplate
    //自动装配JavaMailSender
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailUsername;


    /**
     * 根据id查询小组成员
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable long id) {
        User user = userService.getById(id);
        if (user != null) {
            return R.success(user);
        } else {
            return R.error("不存在该用户！");
        }
    }

    /**
     * 小组成员的添加
     */
    @PostMapping("/save")
    public R save(User user) {
        return userService.saveUser(user);
    }

    /**
     * 小组成员的删除
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable long id) {
        User user = userService.getById(id);
        if (user != null) {
            userService.deleteUser(id);
            return R.success("success!");
        } else {
            return R.error("不存在该用户！");
        }
    }


    /**
     * 小组成员的修改
     */
    @PutMapping("/update")
    public R update(@RequestParam Map<String, Object> map) {
        String id = (String) map.get("id");
        if (id.length() == 0) {
            return R.error("请输入id！");
        }
        userService.updateUser(map);
        return R.success("success!");
    }

    /**
     * 分页查询小组成员
     */
    @GetMapping("/queryPage")
    public R query(@RequestParam Map<String, Object> map) {
        String page = (String) map.get("page");
        String limit = (String) map.get("limit");
        if (page.length() == 0 || limit.length() == 0) {
            return R.error("请传入参数！");
        }
        if (Long.parseLong(page) < 0 || Long.parseLong(limit) < 0) {
            return R.error("请传入正确的参数！");
        }
        return userService.queryUserPage(map);
    }

    /**
     * 根据用户姓名，班级，所学方向，所学时长进行查询
     */
    @GetMapping("/queryLike")
    public R queryLike(@RequestParam Map<String, Object> map) {
        return userService.queryUserLike(map);
    }

    //随机生成6位数字
    public String getCode(String mail) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        //将随机得到的验证码存入redis中设置过期时间为60s
//        redisTemplate.opsForValue().set("RedisCode", stringBuilder.toString(), 60, TimeUnit.SECONDS);
        String code = stringBuilder.toString();

        log.info("==============验证码=" + code);

        redisTemplate.opsForValue().set(mail, code, 60, TimeUnit.SECONDS);
        return stringBuilder.toString();
    }

    //发送邮件
    @GetMapping("/email")
    public R sendEmail(String toMail) {
        SimpleMailMessage massage = new SimpleMailMessage();
        massage.setFrom(mailUsername);
        massage.setTo(toMail);
        massage.setSubject("黑客帝国为你服务，请收好你的验证码：");
        massage.setText(getCode(toMail));//发送内容为验证码
        mailSender.send(massage);
        return R.success("发送成功!");
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public R login(@RequestParam Map<String, Object> map) {
//        String mail = (String) map.get("mail");
//        String code = (String) map.get("code");
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        //   log.info(code.toString());

        //从Session中获取保存的验证码
        // Object codeInSession = session.getAttribute(phone);

        //从redis获取缓存的验证码
//        Object codeInSession = redisTemplate.opsForValue().get(mail);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
//        if (codeInSession != null && codeInSession.equals(code)) {
//            //如果能够比对成功，说明登录成功
//
//            //如果用户登录成功，删除redis中缓存的验证码
//            redisTemplate.delete(mail);
//
//            log.info("==================验证码校验成功！====================");
        boolean flag = userService.queryPasswordByUsername(username, password);
        if (flag) {
            return R.success("登录成功！");
        } else {
            return R.error("密码错误！");
        }
//        }
//        return R.error("验证码错误！");
    }

    /**
     * 根据用户昵称修改密码（昵称唯一性）
     */
    @PostMapping("/updatePasswordByName")
    public R updatePasswordByName(@RequestParam Map<String, Object> map) {
        return userService.updatePasswordByName(map);
    }

    /**
     * 注册
     */
    @PostMapping("/addUser")
    public R addUser(@RequestParam Map<String, Object> map) {
        String mail = (String) map.get("mail");
        String code = (String) map.get("code");

        //从redis获取缓存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(mail);

        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if (codeInSession != null && codeInSession.equals(code)) {
            //如果能够比对成功，说明登录成功

            //如果用户登录成功，删除redis中缓存的验证码
            redisTemplate.delete(mail);

            log.info("==================验证码校验成功！====================");
            return userService.addUser(map);
        }
        return R.error("注册失败！");
    }
}
