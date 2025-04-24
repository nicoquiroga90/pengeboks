package com.pengeboks.deposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DepositServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));

        System.setProperty("SUPABASE_URL", dotenv.get("SUPABASE_URL"));
        System.setProperty("SUPABASE_SERVICE_ROLE_KEY", dotenv.get("SUPABASE_SERVICE_ROLE_KEY"));

        SpringApplication.run(DepositServiceApplication.class, args);
    }
}
