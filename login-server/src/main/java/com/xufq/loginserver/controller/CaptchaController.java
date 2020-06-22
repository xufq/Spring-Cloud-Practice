package com.xufq.loginserver.controller;

import com.xufq.loginserver.exception.BusinessException;
import com.xufq.loginserver.exception.InternalException;
import com.xufq.loginserver.utils.CaptchaUtil;
import com.xufq.loginserver.utils.RedisUtil;
import com.xufq.loginserver.vo.ImageCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @ClassName CaptchaController
 * @Description about captcha process
 * @Author fangqiang.xu
 * @Date 2/6/2020 01:49 PM
 * @Version 1.0
 */
@Controller
@RequestMapping("/captcha")
@Slf4j
public class CaptchaController {

    private final long effectiveTime = 15 * 60;

    private RedisUtil redisUtil;

    public CaptchaController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @GetMapping
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            String authenticationId = request.getHeader("AuthenticationId");
            if (StringUtils.isEmpty(authenticationId)) {
                throw new BusinessException("AuthenticationId不存在！");
            }
            CaptchaUtil captchaUtil = new CaptchaUtil();
            BufferedImage image = captchaUtil.getImage();
            LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(effectiveTime);
            ImageCodeVo imageCodeVo = new ImageCodeVo();
            imageCodeVo.setImageCode(captchaUtil.getText());
            imageCodeVo.setExpiredDate(expiredDate);
            redisUtil.set(getCaptchaKey(authenticationId), imageCodeVo);
            CaptchaUtil.output(image, response.getOutputStream());
        } catch (IOException e) {
            log.warn("图片输出错误！");
            throw new InternalException();
        }
    }

    private String getCaptchaKey(String authenticationId){
        return "CaptchaKey:"+authenticationId;
    }
}
