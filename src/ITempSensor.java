/*
 * Initial Author
 *      Alex Leute
 *
 * Other Contributors
 *
 * Acknowledgements
 */
/**
 * Interface for a temperature sensor
 */
public interface ITempSensor {
    /**
     * This method should return the current reading of the sensor, in Kelvin, as a double
     * @return The current reading of the sensor in Kelvin
     */
    public double reading();
}
