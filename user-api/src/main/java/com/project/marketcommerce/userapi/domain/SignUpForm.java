package com.project.marketcommerce.userapi.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {

  private String user_name;
  private String phone_number;
  private LocalDate birth;
  private String email;
  private String password;

}
