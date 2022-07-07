package org.francis.dh.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Franc1s
 * @date 2022/6/17
 * @apiNote
 */
@ComponentScan(basePackages = {"org.francis.dh"})
@EnableCaching
@SpringBootApplication
public class DHBBSApplication {
    private static final Logger log= LoggerFactory.getLogger(DHBBSApplication.class);
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DHBBSApplication.class, args);
        ConfigurableEnvironment env = context.getEnvironment();
        String port = env.getProperty("server.port");
        String profiles= env.getProperty("spring.profiles.active");
        if ("dev".equals(profiles)){
            log.info("\n----------------------------------------------------------\n\t" +
                    "dh-bbs论坛后台管理系统已启动!\n\t" +
                    "接口文档地址: http://localhost:" + port + "/doc.html\n"+
                    "----------------------------------------------------------");
        }
    }
}
