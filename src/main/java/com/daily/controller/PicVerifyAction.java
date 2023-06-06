package com.daily.controller;

import com.daily.config.R;
import com.daily.utils.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/verify")
public class PicVerifyAction {
    private final static Logger logger = LoggerFactory.getLogger(PicVerifyAction.class);

    /**
     * 生成验证码
     */
    @PostMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {

        try {

            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");

            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);

            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();

            randomValidateCode.getRandomCode(request, response);//输出验证码图片方法

        } catch (Exception e) {

            logger.error("获取验证码失败>>>>   ", e);

        }

    }

    /**
     * 校验验证码
     */
    @PostMapping(value = "/checkVerify", headers = "Accept=application/json")
    public R checkVerify(@RequestParam String verifyInput, HttpSession session) {
        try {

            //从session中获取随机数
            String inputStr = verifyInput;

            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");

            if (random == null || "".equals(random) || !random.equalsIgnoreCase(inputStr)) {
                return R.error("failed");
            } else {
                return R.success("success!");
            }

        } catch (Exception e) {
            logger.error("验证码校验失败", e);
            return R.error("failed");
        }
    }

}

