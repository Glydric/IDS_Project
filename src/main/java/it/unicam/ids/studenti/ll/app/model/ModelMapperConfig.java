package it.unicam.ids.studenti.ll.app.model;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class ModelMapperConfig {

    @Bean
    public static ModelMapper mapper() {
        return new ModelMapper();
    }
}
