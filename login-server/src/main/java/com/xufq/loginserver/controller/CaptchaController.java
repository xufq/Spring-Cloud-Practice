package com.xufq.loginserver.controller;

import com.xufq.loginserver.utils.CaptchaUtil;
import com.xufq.loginserver.vo.ImageCodeVo;
import com.xufq.practicecore.exception.InternalException;
import com.xufq.practicecore.security.SessionConstants;
import com.xufq.practicecore.utils.ObjectMapperUtil;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @ClassName CaptchaController
 * @Description TODO
 * @Author fangqiang.xu
 * @Date 2/6/2020 01:49 PM
 * @Version 1.0
 */
@Controller("/captcha")
@Log
public class CaptchaController {

    private final long effectiveTime = 15 * 60;

    @GetMapping
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil captchaUtil = new CaptchaUtil();
            BufferedImage image = captchaUtil.getImage();
            LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(effectiveTime);
            ImageCodeVo imageCodeVo = ImageCodeVo.builder()
                    .imageCode(captchaUtil.getText())
                    .expiredDate(expiredDate)
                    .build();
            request.getSession().setAttribute(SessionConstants.SESSION_CAPTCHA_KEY, ObjectMapperUtil.getMapper().writeValueAsString(imageCodeVo));
            CaptchaUtil.output(image, response.getOutputStream());
        } catch (IOException e){
            // TODO log message
            log.warning("");
            throw new InternalException();
        }
    }
}
