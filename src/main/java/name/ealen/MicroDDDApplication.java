package name.ealen;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by EalenXie on 2018/8/24 15:16.
 */
@SpringCloudApplication
public class MicroDDDApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroDDDApplication.class, args);
    }

}
