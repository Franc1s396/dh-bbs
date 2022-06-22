package org.francis.dh.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.francis.dh.common.core.entity.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Franc1s
 * @date 2022/4/20
 * @apiNote
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        RespResult respBean = RespResult.error().message("认证失败,无法访问系统资源!");
        objectMapper.writeValue(response.getWriter(), respBean);
    }
}
