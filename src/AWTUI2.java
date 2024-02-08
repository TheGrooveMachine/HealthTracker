/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 *
 * Acknowledgements
 */

 /*
 * AWTUI2 class used for displaying the information from the
 * associated weather station object.
 * This is an extension of JFrame, the outermost container in
 * a AWT application.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class AWTUI2 extends Frame implements Observer {

    private Label celsiusField;   // put current celsius reading here
    private Label kelvinField;    // put current kelvin reading here
    private Label fahrenheitField;   // put current fahrenheit reading here
    private Label inchesField;   // put current inches reading here
    private Label millibarsField;   // put current millibars reading here

    private final WeatherStation station;

    /*
     * A Font object contains information on the font to be used to
     * render text.
     */
    private static Font labelFont
            = new Font(Font.SERIF, Font.PLAIN, 36);

    /*
     * Create and populate the AWTUI2 JFrame with panels and labels to
     * show the temperatures.
     */
    public AWTUI2(WeatherStation station) {
        this.station = station;
        this.station.addObserver(this);

        /*
         * WeatherStation frame is a grid of 1 row by an indefinite number
         * of columns.
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
         * Set up the window's default close operation and pack its elements.
         */
        addWindowListener(
                new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        /*
         * Pack the components in this frame and make the frame visible.
         */
        pack();
        setVisible(true);
    }

    /*
     * Create a new panel on display
     */
    private Label addPanel(String title) {
        Panel panel = new Panel(new GridLayout(2, 1));
        add(panel);
        createLabel(title, panel);
        return createLabel("", panel);
    }

    /*
     * Set the label of the passed field with the passed temp
     */
    public void setLabel(double temp, Label field) {
        field.setText(String.format("%6.2f", temp));
    }

    /*
     * Create a Label with the initial value <title>, place it in
     * the specified <panel>, and return a reference to the Label
     * in case the caller wants to remember it.
     */
    private Label createLabel(String title, Panel panel) {
        Label label = new Label(title);

        label.setAlignment(Label.CENTER);
        label.setFont(labelFont);
        panel.add(label);

        return label;
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
        AWTUI2 ui = new AWTUI2(ws);

        thread.start();
    }
}
