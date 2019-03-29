package com.enter.repair2;

import com.enter.repair2.thread.TokenTimer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@SpringBootApplication
public class Repair2Application {

    public static void main(String[] args) {
		SpringApplication.run(Repair2Application.class, args);
        TokenTimer.start();
    }
}
