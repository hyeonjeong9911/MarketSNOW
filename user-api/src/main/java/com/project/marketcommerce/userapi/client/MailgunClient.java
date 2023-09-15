package com.project.marketcommerce.userapi.client;

import com.project.marketcommerce.userapi.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

  @PostMapping("sandboxc4056d2fca8543a08e840b4e9b9f74be.mailgun.org/messages")
  ResponseEntity<String> sendEmail(@SpringQueryMap SendMailForm form);

}
