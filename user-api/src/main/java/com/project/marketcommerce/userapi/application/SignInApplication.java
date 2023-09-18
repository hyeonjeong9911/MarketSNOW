package com.project.marketcommerce.userapi.application;

import com.project.marketcommerce.userapi.domain.SignInForm;
import com.project.marketcommerce.userapi.domain.model.Customer;
import com.project.marketcommerce.userapi.exception.CustomException;
import com.project.marketcommerce.userapi.service.CustomerService;
import com.project.marketcommercedomain.common.UserType;
import com.project.marketcommercedomain.config.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.marketcommerce.userapi.exception.ErrorCode.LOGIN_CHECK_FAIL;
@Service
@RequiredArgsConstructor
public class SignInApplication {

  private final CustomerService customerService;
  private final JwtAuthenticationProvider provider;
  public String customerLoginToken(SignInForm form) {
    // 1. 로그인 가능 여부
    Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));
    // 2. 토큰 발행

    // 3. 토큰을 response 한다.
    return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
  }

}
