package dealzo.config;

import dealzo.repository.DealRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource("classpath:mongodb.properties")
@EnableMongoRepositories(basePackageClasses = DealRepository.class ,mongoTemplateRef = "mongoTemplate")
public class MongoDBConfig {

}