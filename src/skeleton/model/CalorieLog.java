package skeleton.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalorieLog implements Log {
    private LocalDate date;
    private double calories;


    public CalorieLog(LocalDate date, double calories){
        this.date = date;
        this.calories = calories;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public double getValue() {
        return calories;
    }

    @Override
    public void setValue(double value) {
        calories = value;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public List<String> getAll() {
        List<String> all = new ArrayList<String>(5);
        String yyyy = String.format("%04d", date.getYear());
        String mm = String.format("%02d", date.getMonthValue());
        String dd = String.format("%02d", date.getDayOfMonth());
        all.add(yyyy);
        all.add(mm);
        all.add(dd);
        all.add("c");
        all.add(Double.toString(calories));
        return all;
    }

    @Override
    public String toString() {
        String all = "";
        for (String s : getAll()) {
            all += s;
            all += '\n';
        }
        return all;
    }
    
}
