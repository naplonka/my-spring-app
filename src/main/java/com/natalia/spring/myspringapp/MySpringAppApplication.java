package com.natalia.spring.myspringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class MySpringAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringAppApplication.class, args);
    }
    //Przed tym ja nie było walidacji czyli metody: configureValidatingRepositoryEventListener, i wysłaliśmy np puste desc dostawaliśmy 500
    //dzięki validacji komunikat jest popprawny 400


    @Bean//annotacja bean oznacza że metoda jest widoczna przez springa
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
