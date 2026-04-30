package ${pkg};

import ${pkg}.redis.RedisService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Component("InitRun")
public class InitRun implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitRun.class);

    @Resource
    private DataSource dataSource;

    @Resource
    private RedisService<String, String> redisUtils;


    @Override
    public void run(ApplicationArguments args) {
        try {
            dataSource.getConnection();
            redisUtils.setValue("test", "test", 5, TimeUnit.MINUTES);
            redisUtils.getValue("test");
            logger.info("服务启动成功，可以开发其他模块了");
        } catch (SQLException e) {
            logger.error("数据库配置错误，请检查数据库配置");
        } catch (RedisConnectionFailureException e) {
            logger.error("redis配置错误，请检查redis配置");
        } catch (Exception e) {
            logger.error("服务没启动");
        }
    }
}