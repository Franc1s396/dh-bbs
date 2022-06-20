package org.francis.dh.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Franc1s
 * @date 2022/6/17
 * @apiNote
 */
@ComponentScan(basePackages = {"org.francis.dh"})
@SpringBootApplication
public class DHBBSApplication {
    public static void main(String[] args) {
        SpringApplication.run(DHBBSApplication.class,args);
    }
}
