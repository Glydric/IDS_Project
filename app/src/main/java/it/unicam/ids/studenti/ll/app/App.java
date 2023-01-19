package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.app.model.Office;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {
    Office o;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public static String hello() {
        return "CIAOOOOO!!!!!";
    }

}
