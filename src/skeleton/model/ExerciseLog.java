package skeleton.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import skeleton.Exercise;

public class ExerciseLog implements Log{

    private LocalDate date;
    private Exercise exercise;
    private double calories;
    
    public ExerciseLog(LocalDate date, Exercise exercise, double calories){
        this.date = date;
        this.exercise = exercise;
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

    /**
     * Get the exercise
     * @return exercise
     */
    public Exercise getExercise() {
        return exercise;
    }
    
    @Override
    public void setValue(double calories) {
        this.calories = calories;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get everything - yyyy,mm,dd,f,the exercise's name,calories
     * (slightly different than other implementations of Log)
     * @return {yyyy,mm,dd,f,the exercise's name,calories}
     */
    @Override
    public List<String> getAll() {
        List<String> all = new ArrayList<String>(6);
        String yyyy = String.format("%04d", date.getYear());
        String mm = String.format("%02d", date.getMonth());
        String dd = String.format("%02d", date.getDayOfMonth());
        all.add(yyyy);
        all.add(mm);
        all.add(dd);
        all.add("e");
        all.add(exercise.getName());
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
