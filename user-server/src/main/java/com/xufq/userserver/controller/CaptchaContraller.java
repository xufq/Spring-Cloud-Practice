package com.xufq.userserver.controller;

import com.xufq.userserver.constants.UserConstants;
import com.xufq.userserver.utils.CaptchaUtil;
import com.xufq.userserver.vo.ImageCodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @ClassName CaptchaContraller
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/6/2020 01:49 PM
 * @Version 1.0
 */
@Controller("/captcha")
public class CaptchaContraller {

    private final long effectiveTime = 15 * 60;

    @GetMapping
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil captchaUtil = new CaptchaUtil();
        BufferedImage image = captchaUtil.getImage();
        LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(effectiveTime);
        ImageCodeVo imageCodeVo = ImageCodeVo.builder()
                .imageCode(captchaUtil.getText())
                .expiredDate(expiredDate)
                .build();
        request.getSession().setAttribute(UserConstants.SESSION_CAPTCHA_KEY, imageCodeVo);
        CaptchaUtil.output(image, response.getOutputStream());
    }
}
