package org.coderearth.springkitchens.mailkitchen;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by kunal_patel on 24/10/17.
 */
@Configuration
@ComponentScan(basePackages = "org.coderearth.springkitchens.mailkitchen")
public class AppConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.symcpe.net");
        javaMailSender.setPort(25);
        return javaMailSender;
    }

    @Bean
    public VelocityEngine velocityEngineFactoryBean() throws IOException {
        final VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();

        final Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngineFactoryBean.setVelocityProperties(props);

        return velocityEngineFactoryBean.createVelocityEngine();
    }
}
