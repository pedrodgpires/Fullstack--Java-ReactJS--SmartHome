package smarthome.domain.actuator;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import smarthome.domain.actuator.vo.ActuatorMap;
import smarthome.domain.actuatormodel.vo.ActuatorModelName;
import smarthome.domain.actuatortype.vo.ActuatorTypeName;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of the ActuatorFactory interface.
 * It is responsible for creating instances of Actuator based on the provided actuator data map.
 */

public class ActuatorFactoryImpl implements ActuatorFactory {

    /**
     * A map that associates actuator model names with their corresponding ActuatorFactory instances.
     */
    private Map<String, ActuatorFactory> actuatorFactories;

    /**
     * The constructor reads a properties file and populates the actuatorFactories map.
     * For each actuator model configuration in the properties file, it creates an ActuatorFactory instance using reflection and registers it in the map.
     *
     * @param filePathName The path of the properties file.
     */
    public ActuatorFactoryImpl(String filePathName) {
        actuatorFactories = new HashMap<>();

        Configurations configurations = new Configurations();
        try {
            Configuration configuration = configurations.properties(new File(filePathName));

            // Get the list of actuator model configurations from the properties file
            List<String> actuatorModelConfigs = List.of(configuration.getStringArray("actuatorModelFactory"));

            for (String actuatorModelConfig : actuatorModelConfigs) {
                // Split the actuator model configuration into actuator model name and actuator type name
                String[] actuatorModelConfigArr = actuatorModelConfig.split("\\.");
                ActuatorModelName actuatorModelName = new ActuatorModelName(actuatorModelConfigArr[0]);
                ActuatorTypeName actuatorTypeName = new ActuatorTypeName(actuatorModelConfigArr[1]);

                // Create an ActuatorFactory instance using reflection
                ActuatorFactory creator = (ActuatorFactory) Class.forName("smarthome.domain.actuator." + actuatorTypeName.getActuatorTypeName()).getConstructor().newInstance();
                register(actuatorModelName.getActuatorModelName(), creator);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException |
                 ConfigurationException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method adds an ActuatorFactory instance to the actuatorFactories map.
     *
     * @param actuatorModel The model name of the actuator.
     * @param creator The ActuatorFactory instance.
     */
    private void register(String actuatorModel, ActuatorFactory creator) {
        actuatorFactories.put(actuatorModel, creator);
    }

    /**
     * This method creates an Actuator based on the provided actuator data map.
     * It retrieves the actuator model name from the data map, finds the corresponding ActuatorFactory in the registry, and uses it to create and return a new actuator.
     *
     * @param actuatorDataMap The data map for creating an actuator.
     * @return The created Actuator.
     */
    @Override
    public Actuator createActuator(ActuatorMap actuatorDataMap) {
        if (actuatorDataMap == null) {
            throw new IllegalArgumentException();
        }
        // Get the actuator model name from the data map
        ActuatorModelName model = actuatorDataMap.getActuatorModelName();

        // Find the corresponding ActuatorFactory in the registry
        ActuatorFactory creatorOfActuator = actuatorFactories.get(model.getActuatorModelName());
        if (creatorOfActuator == null) {
            throw new IllegalArgumentException();
        }
        // Use the ActuatorFactory to create and return a new actuator
        return creatorOfActuator.createActuator(actuatorDataMap);
    }
}