package smarthome;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.actuator.ActuatorFactoryImpl;

@Configuration
public class FactoryConfig {


    private String configModels = "configModels.properties";


    @Bean
    public ActuatorFactory actuatorFactory() {
        return new ActuatorFactoryImpl(configModels);
    }


}