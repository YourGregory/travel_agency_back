package com.kursova.travel.service.base;

import com.kursova.travel.constants.EmailConstant;
import com.kursova.travel.email.EmailBuilder;
import com.kursova.travel.entity.base.AbstractUser;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractEmailService {

    static final String MAIN_LAYOUT = "layouts/main.ftl";

    final JavaMailSender javaMailSender;
    final Configuration freemarkerConfig;
    final Boolean testEnvironment;

    public AbstractEmailService(JavaMailSender mailSender, Configuration freemarkerConfig, Boolean testEnvironment) {
        this.javaMailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.testEnvironment = testEnvironment;
    }

    protected void sendEmail(EmailBuilder emailBuilder) {
        String body = processTemplate(emailBuilder.getModel(), emailBuilder.getAbstractUser());
        sendEmail(emailBuilder.getAbstractUser().getEmail(), emailBuilder.getSubject(), body);
    }

    /**
     * Sends email without attachments where content is HTML.
     */
    private void sendEmail(@NonNull final String recipient, @NonNull final String subject, @NonNull final String body) {
        if (BooleanUtils.isFalse(testEnvironment)) {
            javaMailSender.send(message -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(
                        message, false, StandardCharsets.UTF_8.displayName()
                );
                messageHelper.setTo(recipient);
                messageHelper.setSubject(subject);
                messageHelper.setText(body, true);
                messageHelper.setFrom("noReply@travel-agency.com");
            });
        }
    }

    @SneakyThrows
    private String processTemplate(Map<String, String> values, AbstractUser abstractUser) {
        Map<String, String> layoutParams = getLayoutParams(abstractUser);
        values.putAll(layoutParams);

        Template template = freemarkerConfig.getTemplate(MAIN_LAYOUT);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, values);
    }

    private Map<String, String> getLayoutParams(AbstractUser abstractUser) {
        return Map.of(
                EmailConstant.YEAR, String.valueOf(LocalDate.now().getYear()),
                EmailConstant.FIRST_NAME, abstractUser.getFirstName(),
                EmailConstant.SUPPORT_EMAIL, "support@travel-agency.com"
        );
    }

}
