package com.project.marketcommerce.userapi.service.test;

import com.project.marketcommerce.userapi.client.MailgunClient;
import com.project.marketcommerce.userapi.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

  private final MailgunClient mailgunClient;

  public String sendEmail() {

    SendMailForm form = SendMailForm.builder()
        .from("marketCommerce@my.com")
        .to("hyunjung6234@daum.net")
        .subject("[마켓]회원가입을 위해 메일을 인증해주세요.")
        .text("안녕하세요. 마켓입니다.\n회원가입을 위해 메일을 인증해주세요.")
        .build();

    return mailgunClient.sendEmail(form).getBody();

  }
}
