/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

 /*
 * The TextUI2 class is an observer of the WeatherStation that,
 * when it receives an update message, prints the temperature
 * in Celsius, Kelvin, Fahrenheit, Inches, and Millibars.
 *
 * The main method for the text based monitoring application
 * is here as well.
 */
import java.util.Observer;
import java.util.Observable;

public class TextUI2 implements Observer {

    private final WeatherStation station;

    /*
     * Remember the station we're attached to and
     * add ourselves as an observer.
     */
    public TextUI2(WeatherStation station) {
        this.station = station;
        this.station.addObserver(this);
    }

    /*
     * Called when WeatherStation gets another reading.
     * The Observable should be the station; the Object
     * argument is ignored.
     */
    @Override
    public void update(Observable obs, Object ignore) {
        /*
         * Check for spurious updates from unrelated objects.
         */
        if (station != obs) {
            return;
        }
        /*
         * Retrieve and print the temperatures and pressures.
         */
        System.out.printf(
                "Temperature: %6.2f C %6.2f F  %6.2f K %n"
                + "Pressure: %9.2f inches  %6.2f mbar %n",
                station.getCelsius(), station.getFahrenheit(), station.getKelvin(),
                station.getInches(), station.getMillibars());
    }

    /*
     * Start the application.
     */
    public static void main(String[] args) {
         /*
         * Create the sensor objects to inject
         * into the WeatherStation constructor
         */
        ITempSensor temp = new KelvinTempSensorAdapter(); // Adaptation of KelvinTempSensor to ITempSensor interface
        IBarometer bar = new Barometer();

        WeatherStation ws = new WeatherStation(temp, bar);
        Thread thread = new Thread(ws);
        TextUI2 ui = new TextUI2(ws);

        thread.start();
    }
}
