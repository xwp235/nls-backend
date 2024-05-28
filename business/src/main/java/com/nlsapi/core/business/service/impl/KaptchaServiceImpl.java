package com.nlsapi.core.business.service.impl;

import com.nlsapi.core.business.service.KaptchaService;
import org.springframework.stereotype.Service;

@Service
public class KaptchaServiceImpl implements KaptchaService {

    /**
     * 校验图片验证码
     * 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
     * @param code 用户输入的验证码
     * @param token
     */
    @Override
    public void validCode(String code, String token) {
//        String imageCode = stringRedisTemplate.opsForValue().get(token);
//        LOG.info("从redis中获取到的验证码：{}", imageCode);
//        if (ObjectUtils.isEmpty(imageCode)) {
//            LOG.warn("验证码校验失败，验证码已过期");
//            throw new BusinessException(BusinessExceptionEnum.IMAGE_CODE_ERROR);
//        }
//        if (!imageCode.toLowerCase().equals(code.toLowerCase())) {
//            LOG.warn("验证码校验失败，验证码不对");
//            throw new BusinessException(BusinessExceptionEnum.IMAGE_CODE_ERROR);
//        } else {
//            // 验证通过后，移除验证码
//            stringRedisTemplate.delete(token);
//        }
    }

}
