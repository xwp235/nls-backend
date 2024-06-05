package com.nlsapi.core.business.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nlsapi.core.business.context.LoginMemberContext;
import com.nlsapi.core.business.resp.MemberLoginResp;
import com.nlsapi.core.common.utils.JwtUtil;
import com.nlsapi.core.common.utils.LogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class WebLoginInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURL().toString();
        LogUtil.info("接口登录拦截，path：{}", path);

        //获取header的token参数
        String token = request.getHeader("token");
        LogUtil.info("网站登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LogUtil.info( "token为空，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else if (!JwtUtil.validate(token)) {
            LogUtil.info( "token校验不通过，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            LogUtil.info("获取会员登录token：{}", token);
            JSONObject loginMember = JwtUtil.getJSONObject(token);
            LogUtil.info("当前登录会员：{}", loginMember);
            MemberLoginResp member = JSONUtil.toBean(loginMember, MemberLoginResp.class);
            member.setToken(token);
            LoginMemberContext.setMember(member);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LoginMemberContext.removeMember();
    }
}
