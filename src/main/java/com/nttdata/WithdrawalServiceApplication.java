package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**Se crea la clase principal WithdrawalServiceApplication.*/
@EnableEurekaClient
@SpringBootApplication
public class WithdrawalServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(WithdrawalServiceApplication.class, args);
  }

}
