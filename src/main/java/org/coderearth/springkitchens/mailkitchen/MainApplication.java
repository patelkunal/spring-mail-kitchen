package org.coderearth.springkitchens.mailkitchen;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class MainApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Staring up MainApplication !!");
        final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        JavaMailSender mailSender = context.getBean(JavaMailSender.class);
        Assert.notNull(mailSender, "SampleBean is not yet initialized !!");
        final VelocityEngine velocityEngine = context.getBean(VelocityEngine.class);
        Assert.notNull(velocityEngine, "SampleBean is not yet initialized !!");

        final MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("noreply@symantec.com");
            mimeMessageHelper.setTo("kunal_patel@symantec.com");
            mimeMessageHelper.setSubject("Greetings !!");

            Map<String, Object> model = new HashMap<>(2);
            model.put("foo", "FOO");
            model.put("bar", "BAR");
            model.put("foo_boolean", false);
            model.put("bar_boolean", false);
            final String msgText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/notification_2.vm", "UTF-8", model);
            mimeMessageHelper.setText(msgText, true);
        };

        mailSender.send(preparator);
        LOGGER.info("Shutting down MainApplication !!");
    }
}