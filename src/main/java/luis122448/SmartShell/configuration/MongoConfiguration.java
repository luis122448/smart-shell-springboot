package luis122448.SmartShell.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
    basePackages = {"luis122448.SmartShell.application.archive.persistence.repository"}
)
public class MongoConfiguration {

}
