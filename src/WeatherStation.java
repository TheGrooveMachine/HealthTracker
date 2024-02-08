/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

 /*
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 *
 * The class also extends Observable so that it can notify registered
 * objects whenever its state changes. Convenience functions are provided
 * to access the temperature in different schemes (Celsius, Kelvin, etc.)
 */
import java.util.Observable;

public class WeatherStation extends Observable implements Runnable {

    private final ITempSensor sensor;      // Temperature sensor.
    private final IBarometer barometer;          //Barometer

    private final long PERIOD = 1000;           // 1 sec = 1000 ms
    private final double KTOC = -273.15;            // Kelvin to Celsius conversion.
    private final double ITOM = 338.64;             // Inches to Millibar convertion

    private double currentReading;
    private double currentPressure;

    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object and baromoter it will use.
     */
    public WeatherStation(ITempSensor temp, IBarometer bar) {
        sensor = temp;
        currentReading = sensor.reading();

        barometer = bar;
        currentPressure = barometer.pressure();
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from its
     * sensor, and notifies registered Observers of the change.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(PERIOD);
            } catch (Exception e) {
            }    // ignore exceptions

            /*
             * Get next reading and notify any Observers.
             */
            synchronized (this) {
                currentReading = sensor.reading();
                currentPressure = barometer.pressure();
            }
            setChanged();
            notifyObservers();
        }
    }

    /*
     * Return the current reading in degrees celsius as a
     * double precision number.
     */
    public synchronized double getCelsius() {
        return (currentReading + KTOC);
    }

    /*
     * Return the current reading in degrees Kelvin as a
     * double precision number.
     */
    public synchronized double getKelvin() {
        return currentReading;
    }

    /*
     * Return the current reading in degrees fahrenheit as a
     * double precision number.
     */
    public synchronized double getFahrenheit() {
        return ((currentReading + KTOC)) * (9.0 / 5.0) + 32.0;
    }

    /*
     * Return the current pressure in inches as a
     * double precision number.
     */
    public synchronized double getInches() {
        return currentPressure;
    }

    /*
     * Return the current pressure in millibars as a
     * double precision number.
     */
    public synchronized double getMillibars() {
        return (currentPressure * ITOM) / 1000.0;
    }
}
