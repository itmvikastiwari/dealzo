package dealzo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EnableAutoConfiguration
@EnableCaching
public class DealzoApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DealzoApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}