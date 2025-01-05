package smarthome.domain.actuator;


import smarthome.domain.actuator.vo.ActuatorId;
import smarthome.domain.actuator.vo.ActuatorMap;
import smarthome.domain.actuator.vo.DecimalLimit;
import smarthome.domain.actuator.vo.Precision;
import smarthome.domain.actuatormodel.vo.ActuatorModelName;
import smarthome.domain.device.vo.DeviceId;


/**
 * This class represents an ActuatorFactory for ActuatorOfDecimalLimiter objects.
 */
public class ActuatorOfDecimalLimiterFactory implements ActuatorFactory{

    /**
     * Constructs an ActuatorOfDecimalLimiterFactory object.
     */
    public ActuatorOfDecimalLimiterFactory() {
    }

    /**
     * Creates an Actuator object using the provided ActuatorMap data.
     *
     * @param actuatorData The ActuatorMap object containing the necessary data to create an Actuator.
     * @return An ActuatorOfDecimalLimiter object constructed using the provided ActuatorMap data.
     */
    public Actuator createActuator(ActuatorMap actuatorData) {
        DeviceId deviceId = actuatorData.getDeviceId();
        ActuatorModelName actuatorModelName = actuatorData.getActuatorModelName();
        DecimalLimit lowerLimit = actuatorData.getDecimalLowerLimit();
        DecimalLimit upperLimit = actuatorData.getDecimalUpperLimit();
        Precision precision = actuatorData.getPrecision();
        if (actuatorData.getActuatorId() == null) {
            return new ActuatorOfDecimalLimiter(deviceId, actuatorModelName,
                    lowerLimit, upperLimit, precision);
        }
        ActuatorId actuatorId = actuatorData.getActuatorId();
        return new ActuatorOfDecimalLimiter(actuatorId, deviceId, actuatorModelName,
                lowerLimit, upperLimit, precision);
    }

}
