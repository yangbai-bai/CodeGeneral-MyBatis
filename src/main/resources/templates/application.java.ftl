package ${pkg};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableAsync
@SpringBootApplication(scanBasePackages = {"${pkg}"})
@MapperScan("${pkg}.mapper")
@EnableTransactionManagement
@EnableScheduling
public class ${applicationName}Application {
    public static void main(String[] args) {
        SpringApplication.run(${applicationName}Application.class, args);
    }
}
