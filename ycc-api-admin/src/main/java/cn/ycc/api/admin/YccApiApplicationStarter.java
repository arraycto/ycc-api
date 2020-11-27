package cn.ycc.api.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("cn.ycc.api.admin.mapper")
public class YccApiApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(YccApiApplicationStarter.class);
    }

}
