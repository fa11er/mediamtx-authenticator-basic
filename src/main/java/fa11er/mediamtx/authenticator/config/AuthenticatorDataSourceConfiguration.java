package fa11er.mediamtx.authenticator.config;

import java.util.HashMap;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories
        (basePackages = "fa11er.mediamtx.authenticator.dao",
                entityManagerFactoryRef = "authenticatorEntityManagerFactory",
                transactionManagerRef = "authenticatorTransactionManager")
public class AuthenticatorDataSourceConfiguration {
    @Autowired
    Environment env;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.authenticator")
    public DataSourceProperties authenticatorDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "app.datasource.hikari")
    public DataSource authenticatorDataSource() {
        return authenticatorDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Bean (name = "authenticatorEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean authenticatorEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(authenticatorDataSource());
        entityManagerFactory.setPackagesToScan("fa11er.mediamtx.authenticator.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.naming.physical-strategy", env.getProperty("hibernate.naming.physical-strategy"));
        properties.put("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults",
                env.getProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"));
        entityManagerFactory.setJpaPropertyMap(properties);
        return entityManagerFactory;
    }

    @Bean
    @Primary
    public PlatformTransactionManager authenticatorTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(authenticatorEntityManagerFactory().getObject());
        return transactionManager;
    }
}
