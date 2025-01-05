package smarthome.persistence.spring;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * This class provides the Spring Data configuration for the application.
 * It configures the entity manager factory and transaction manager for JPA.
 */
@Configuration
@EnableJpaRepositories(basePackages = "smarthome.persistence.spring")
@ComponentScan("smarthome.persistence.spring")
public class SpringDataConfig {

    LocalContainerEntityManagerFactoryBean manager;

    /**
     * This method creates and configures the entity manager factory for JPA.
     *
     * @return The entity manager factory bean.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        manager = new LocalContainerEntityManagerFactoryBean();
        manager.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        manager.setPersistenceUnitName("smarthome");
        manager.setPackagesToScan("smarthome.persistence.datamodel");
        manager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return manager;
    }

    /**
     * This method creates and configures the transaction manager for JPA.
     *
     * @param entityManagerFactory The entity manager factory.
     * @return The transaction manager.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
