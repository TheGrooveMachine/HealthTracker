/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

 /*
 * The SwingUI2 class is an observer of the WeatherStation that,
 * when it receives an update message, prints the temperature
 * in Celsius, Kelvin, Fahrenheit, Inches, and Millibars.
 *
 * The main method for the Swing GUI based monitoring application
 * is here as well.
 */
import java.util.Observer;
import java.util.Observable;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import java.text.DecimalFormat ;
public class SwingUI2 extends JFrame implements Observer {

    private JLabel celsiusField;   // put current celsius reading here
    private JLabel kelvinField;    // put current kelvin reading here
    private JLabel fahrenheitField;   // put current fahrenheit reading here
    private JLabel inchesField;   // put current inches reading here
    private JLabel millibarsField;   // put current millibars reading here

    private final WeatherStation station;
    /*
     * A Font object contains information on the font to be used to
     * render text.
     */
    private static Font labelFont
            = new Font(Font.SERIF, Font.PLAIN, 36);

    /*
     * Create and populate the SwingUI2 JFrame with panels and labels to
     * show the temperatures.
     *
     * Remember the station we're attached to and
     * add ourselves as an observer.
     */
    public SwingUI2(WeatherStation station) {
        this.station = station;
        this.station.addObserver(this);

        /*
         * WeatherStation frame is a grid of 1 row by an indefinite
         * number of columns.
         */
        this.setLayout(new GridLayout(1, 0));
        
        /*
         * Set the title of the frame
         */
        this.setTitle("Weather Station");

        /*
         * There are two panels, one each for Kelvin and Celsius, added to the
         * frame. Each Panel is a 2 row by 1 column grid, with the temperature
         * name in the first row and the temperature itself in the second row.
         *
         * Set up Kelvin display.
         */
        kelvinField = addPanel(" Kelvin ");

        /*
         * Set up Celsius display.
         */
        celsiusField = addPanel(" Celsius ");
        
        /*
         * Set up Fahrenheit display.
         */
        fahrenheitField = addPanel(" Fahrenheit ");
        
        /*
         * Set up Inches display.
         */
        inchesField = addPanel(" Inches ");
        
        /*
         * Set up Millibars display.
         */
        millibarsField = addPanel(" Millibars ");

        /*
         * Set up the frame's default close operation pack its elements,
         * and make the frame visible.
         */
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /*
     * Set the label of the passed field with the passed value
     */
    private void setLabel(double value, JLabel field) {
        field.setText(String.format("%6.2f", value));
    }

    /*
     * Create a Label with the initial value <title>, place it in
     * the specified <panel>, and return a reference to the Label
     * in case the caller wants to remember it.
     */
    private JLabel createLabel(String title, JPanel panel) {
        JLabel label = new JLabel(title);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(labelFont);
        panel.add(label);

        return label;
    }

    /*
     * Create a new panel on display
     */
    private JLabel addPanel(String title) {
        JPanel panel;
        panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(title, panel);
        return createLabel("", panel);
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
         * Retrieve and set the temperature values in the lables.
         */
        setLabel(station.getCelsius(), celsiusField);
        setLabel(station.getKelvin(), kelvinField);
        setLabel(station.getFahrenheit(), fahrenheitField);
        setLabel(station.getInches(), inchesField);
        setLabel(station.getMillibars(), millibarsField);
        
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
        SwingUI2 ui = new SwingUI2(ws);

        thread.start();
    }
}
