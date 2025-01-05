package smarthome;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IReadingRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.service.IActuatorService;
import smarthome.service.IReadingService;
import smarthome.service.impl.ActuatorServiceImpl;
import smarthome.service.impl.ReadingServiceImpl;

@Configuration
public class
AppConfig {


    private String configDelta = "configDelta.properties";
    private String configCloseBlindsSensor = "configModels.properties";
    @Autowired
    private IReadingRepository readingRepo;
    @Autowired
    private ISensorRepository sensorRepo;
    @Autowired
    private IDeviceRepository deviceRepository;
    @Autowired
    private IActuatorRepository actuatorRepository;
    @Autowired
    private ISensorRepository sensorRepository;
    @Autowired
    private IReadingRepository readingRepository;
    @Autowired
    private ActuatorFactory actuatorFactory;

    @Bean
    public String filePathModels() {
        return "configModels.properties";
    }

    @Bean
    public String filePathDelta() {
        return "configDelta.properties";
    }
}