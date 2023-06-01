package com.daily;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author a1002
 */
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@Slf4j
public class InnovationDailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnovationDailyApplication.class, args);
        log.info("项目启动成功。。。。。。。");
    }

}
