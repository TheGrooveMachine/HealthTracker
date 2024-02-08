/*
 * Initial Author
 *      Griffin Moose
 *
 * Other Contributors
 *
 * Acknowledgements
 */
/**
 * Interface for a barometer
 */
public interface IBarometer {
    /**
     * This method is for getting the pressure being read (or simulated) by a barometer
     * in inches of mercury as a double
     *  @return The current pressure read by the barometer as a double
     */
    public double pressure();
}
