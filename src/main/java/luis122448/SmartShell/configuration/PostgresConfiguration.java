package luis122448.SmartShell.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"luis122448.SmartShell.security.application.repository",
                "luis122448.SmartShell.application.domain.persistence.repository",
                "luis122448.SmartShell.application.domain.persistence.repository.procedure"},
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresPlatformTransactionManager"
)
public class PostgresConfiguration {

//    @Primary
//    @Bean(name="postgresDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
//    public DataSource postgresDataSource(){
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name = "postgresPropertiesDataSource")
    @ConfigurationProperties(prefix="spring.datasource.postgresql")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource datasource(@Qualifier("postgresPropertiesDataSource") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name="postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory
            (EntityManagerFactoryBuilder builder,
             @Qualifier("postgresDataSource") DataSource dataSource){
        String[] packagesToScan = {
                "luis122448.SmartShell.security.application.entity",
                "luis122448.SmartShell.application.domain.persistence.entity",
                "luis122448.SmartShell.application.domain.persistence.view"
        };
        return builder.dataSource(dataSource)
                .packages(packagesToScan)
                .persistenceUnit("postgres").build();
    }

    @Bean(name = "postgresPlatformTransactionManager")
    public PlatformTransactionManager postgresPlatformTransactionManager(
            @Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
