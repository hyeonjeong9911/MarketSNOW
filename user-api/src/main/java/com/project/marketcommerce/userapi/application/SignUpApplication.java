package com.project.marketcommerce.userapi.application;

import com.project.marketcommerce.userapi.client.MailgunClient;
import com.project.marketcommerce.userapi.client.mailgun.SendMailForm;
import com.project.marketcommerce.userapi.domain.SignUpForm;
import com.project.marketcommerce.userapi.domain.model.Customer;
import com.project.marketcommerce.userapi.exception.CustomException;
import com.project.marketcommerce.userapi.exception.ErrorCode;
import com.project.marketcommerce.userapi.service.SignUpCustomerService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {
  private final MailgunClient mailgunClient;
  private final SignUpCustomerService signUpCustomerService;

  public void customerVerify(String email, String code) {
    signUpCustomerService.verifyEmail(email, code);
  }
  public String customerSignUp(SignUpForm form) {

    if (signUpCustomerService.isEmailExist(form.getEmail())) {
      throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
      // exception
    } else  {
      Customer c = signUpCustomerService.signUp(form);
      LocalDateTime now = LocalDateTime.now();

      String code = getRandomCode();
      SendMailForm sendMailForm = SendMailForm.builder()
              .from("marketCommerce_test@mytester.com")
              .to(form.getEmail())
              .subject("Verification Email")
              .text(getVerificationEmailBody(c.getEmail(), c.getUser_name(), code))
                      .build();
      mailgunClient.sendEmail(sendMailForm);
      signUpCustomerService.changeCustomerValidateEmail(c.getId(), code);
      return "회원 가입에 성공하였습니다.";
    }

  }

  private String getRandomCode() {
    return RandomStringUtils.random(10, true, true);
  }

  private String getVerificationEmailBody(String email, String name, String code) {
    StringBuilder builder = new StringBuilder();
    return builder.append("안녕하세요").append(name).append("! 인증을 위해 링크를 클릭해주세요.\n\n")
        .append("http://localhost:8081/customer/verify/customer?email=")
        .append(email)
        .append("&code=")
        .append(code).toString();
  }

}
