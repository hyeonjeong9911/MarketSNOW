package com.project.marketcommerce.userapi.config.filter;

import com.project.marketcommerce.userapi.service.CustomerService;
import com.project.marketcommercedomain.common.UserVo;
import com.project.marketcommercedomain.config.JwtAuthenticationProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import javax.servlet.Filter;

@WebFilter(urlPatterns = "/customer/*")
@RequiredArgsConstructor
public class CustomerFilter implements Filter {

  private final JwtAuthenticationProvider jwtAuthenticationProvider;
  private final CustomerService customerService;


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader("X-AUTH-TOKEN");
    if (!jwtAuthenticationProvider.validateToken(token)) {
      throw new ServletException("Invalid Access");
    }

    UserVo vo = jwtAuthenticationProvider.getUserVo(token);
    customerService.findByIdAndEmail(vo.getId()).orElseThrow(
        () -> new SecurityException("Invalid access")
    );

  }
}
