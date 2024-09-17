package com.token.interceptor;

import com.token.constant.JwtClaimsConstant;
import com.token.constant.RedisKeyConstant;
import com.token.context.BaseContext;
import com.token.properties.JwtProperties;
import com.token.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AdminJwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 校验JWT
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        //  从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //  校验令牌
        try {
            log.info("jwt校验：{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("员工ID:{}", empId);

            String redis_token = (String) redisTemplate.opsForValue().get(RedisKeyConstant.REDIS_ADMIN_TOKEN_KEY_ + empId);
            if (StringUtils.isEmpty(redis_token)) {
                response.setStatus(401);
                return false;
            }

            BaseContext.setCurrentId(empId);
            // 通过 放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    /**
     * 释放ThreadLocal
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.removeCurrentId();
    }
}
