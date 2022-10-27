package com.natalia.spring.myspringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class MySpringAppApplication implements RepositoryRestConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(MySpringAppApplication.class, args);
    }
    //Przed tym ja nie było walidacji czyli metody: configureValidatingRepositoryEventListener, i wysłaliśmy np puste desc dostawaliśmy 500
    //dzięki validacji komunikat jest popprawny 400
    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator());
        validatingListener.addValidator("beforeSave", validator());
    }


    @Bean//annotacja bean oznacza że metoda jest widoczna przez springa
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
