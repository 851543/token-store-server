package com.token.filter;

import com.token.constant.JwtClaimsConstant;
import com.token.constant.MessageConstant;
import com.token.constant.RedisKeyConstant;
import com.token.entity.EmployeeLoginDetails;
import com.token.exception.JwtTokenAuthenticationFilterException;
import com.token.properties.JwtProperties;
import com.token.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@Slf4j
public class AdminJwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private JwtProperties jwtProperties;

    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest resuest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //  获取token
        String token = resuest.getHeader(jwtProperties.getAdminTokenName());
        if (!StringUtils.hasLength(token)) {
            //  放行
            filterChain.doFilter(resuest, response);
            return;
        }

        //  解析token
        Long empId;
        try {
            log.info("过滤器 jwt校验：{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("员工ID:{}", empId);
        } catch (Exception e) {
            throw new JwtTokenAuthenticationFilterException(MessageConstant.TOKEN_ERROR);
        }

        //  从redis中获取员工信息
        EmployeeLoginDetails employeeLoginDetails = (EmployeeLoginDetails) redisTemplate.opsForValue().get(RedisKeyConstant.TOKEN_ADMIN_LOGIN_INFO_KEY_ + empId);
        if (Objects.isNull(employeeLoginDetails)) {
            throw new JwtTokenAuthenticationFilterException(MessageConstant.USER_NOT_LOGIN);
        }

        //  员工信息存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(employeeLoginDetails, null, employeeLoginDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //  放行
        filterChain.doFilter(resuest, response);
    }
}

