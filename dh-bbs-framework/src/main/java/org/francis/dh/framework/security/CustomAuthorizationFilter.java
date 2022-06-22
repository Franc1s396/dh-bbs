package org.francis.dh.framework.security;

import org.francis.dh.common.core.entity.LoginUser;
import org.francis.dh.common.core.entity.User;
import org.francis.dh.system.service.PermsService;
import org.francis.dh.system.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Franc1s
 * @date 2022/1/18
 * @apiNote
 */
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        LoginUser user = tokenService.getLoginUser(request,response);
        if (Objects.nonNull(user)) {
            tokenService.verifyToken(user);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
