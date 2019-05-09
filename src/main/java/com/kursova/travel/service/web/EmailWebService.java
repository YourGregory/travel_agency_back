package com.kursova.travel.service.web;

import com.kursova.travel.constants.EmailConstant;
import com.kursova.travel.email.EmailBuilder;
import com.kursova.travel.entity.base.AbstractUser;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.service.base.AbstractEmailService;
import freemarker.template.Configuration;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailWebService extends AbstractEmailService {

    final Environment environment;

    public EmailWebService(JavaMailSender mailSender, Configuration freemarkerConfig, Environment environment) {
        super(mailSender, freemarkerConfig, Arrays.asList(environment.getActiveProfiles()).contains("test"));
        this.environment = environment;
    }

    public void sendFirstPassword(AbstractUser abstractUser, String password) {
        Map<String, String> model = new HashMap<>();

        model.put(EmailConstant.PASSWORD, password);

        EmailBuilder builder = EmailBuilder.builder()
                .subject("Created")
                .model(model)
                .abstractUser(abstractUser)
                .build();

        sendEmail(builder);
    }

}
