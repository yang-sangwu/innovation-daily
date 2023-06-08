package com.daily.controller;

import com.daily.config.R;
import com.daily.service.StudentSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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
@RequestMapping("/studentSign")
@Slf4j
public class StudentSignController {
    @Resource
    private StudentSignService studentSignService;

    /**
     * 查询某个用户下的所有签到签退情况
     */
    @GetMapping("/querySignByUserId")
    public R querySignByUserId(@RequestParam Map<String, Object> map) {
        String id = (String) map.get("id");
        if (id.length() == 0) {
            return R.error("请输入id！");
        }
        return studentSignService.querySignByUserId(map);
    }

    /**
     * 用户签到
     */
    @PostMapping("/addSign")
    public R addSign(@RequestParam Map<String, Object> map) {
        return studentSignService.addSign(map);
    }

    /**
     * 用户签退
     */
    @PutMapping("/addOut")
    public R addOut(@RequestParam Map<String, Object> map) {
        return studentSignService.addOut(map);
    }

    /**
     * 分页查询签到签退信息
     */
    @GetMapping("/queryPage")
    public R queryStudentSignPage(@RequestParam Map<String, Object> map) {
        return studentSignService.queryStudentSignPage(map);
    }

    /**
     * 2分页查询签到签退信息
     */
    @GetMapping("/queryUserStudentPage")
    public R queryUserStudentPage(@RequestParam Map<String, Object> map) {
        return studentSignService.queryUserStudentPage();
    }

}
