package com.pengeboks.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ReceiptServiceApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("SUPABASE_URL", dotenv.get("SUPABASE_URL"));
        System.setProperty("SUPABASE_BUCKET_NAME", dotenv.get("SUPABASE_BUCKET_NAME"));
        System.setProperty("SUPABASE_SERVICE_ROLE_KEY", dotenv.get("SUPABASE_SERVICE_ROLE_KEY"));
        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));

        SpringApplication.run(ReceiptServiceApplication.class, args);
    }
}