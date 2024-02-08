package skeleton.model;

import java.time.LocalDate;
import java.util.List;

public interface Log {
    /**
     * Get the date as a LocalDate
     * 
     * @return a LocalDate that is the date that was logged
     */
    public LocalDate getDate();

    /**
     * Get the value of the log
     * 
     * @return the value (for example, the weight)
     */
    public double getValue();

    /**
     * Set the value
     * 
     * @param value
     */
    public void setValue(double value);

    /**
     * Set the date. For example, setDate(LocalDate.of(2022, 12, 31))
     * @param date the LocalDate
     */
    public void setDate(LocalDate date);

    /**
     * Get the date, followed by a letter that is short for the value type,
     * followed by the value.
     * Example - {2021,09,30,c,1800.0}
     * 
     * @return a List<String> of the date and the value.
     */
    public List<String> getAll();
}
