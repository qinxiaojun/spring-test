package com.example.springtest.login;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2021-06-21 10:40
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @PostMapping
    public String login(String username, String password, String verCode, HttpServletRequest request){
        if (!CaptchaUtil.ver(verCode,request)){
            CaptchaUtil.clear(request);
            return "verCode error";
        }
        return "success";
    }
}
