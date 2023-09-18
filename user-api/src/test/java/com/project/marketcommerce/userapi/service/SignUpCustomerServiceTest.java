package com.project.marketcommerce.userapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.project.marketcommerce.userapi.domain.SignUpForm;
import com.project.marketcommerce.userapi.domain.model.Customer;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SignUpCustomerServiceTest {

  @Autowired
  private SignUpCustomerService service;

  @Test
  void signUp() {
    SignUpForm form = SignUpForm.builder()
        .user_name("user_name")
        .birth(LocalDate.now())
        .email("abc@gmail.com")
        .password("1")
        .phone_number("01000000000")
        .build();
    Customer c = service.signUp(form);

    assertNotNull(c.getId());
    assertNotNull(c.getCreatedAt());
//    Assert.isTrue(c.signUp(form).getId() != null);
  }
}