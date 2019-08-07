package name.ealen.application.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by EalenXie on 2018/8/24 17:15.
 * Application启动后运行,日志打印微服务关键配置信息(服务名,微服务注册中心地址,配置中心)
 */
@Component
public class ApplicationRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void run(String... strings) {
        Environment environment = applicationContext.getEnvironment();
        try {
            log.info("Application name : {}", environment.getProperty("spring.application.name"));
            log.info("Eureka defaultZone : {}", environment.getProperty("eureka.client.service-url.defaultZone"));
            log.info("ConfigCenter url : {}", environment.getProperty("spring.cloud.config.discovery.service-id"));
        } catch (Exception e) {
            log.warn("Get Properties Exception : {}", e.getMessage());
        }
    }
}
