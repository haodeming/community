package life.majiang.community.communitydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("life.majiang.community.communitydemo.mapper")
public class CommunitydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunitydemoApplication.class, args);
    }

}
