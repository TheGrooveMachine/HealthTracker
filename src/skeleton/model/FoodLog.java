package skeleton.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import skeleton.Food;

public class FoodLog implements Log{
    private LocalDate date;
    private Food food;
    private double servings;

    public FoodLog(LocalDate date, Food food, double servings){
        this.date = date;
        this.food = food;
        this.servings = servings;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public double getValue() {
        return servings;
    }

    /**
     * Get the food
     * @return food
     */
    public Food getFood() {
        return food;
    }

    @Override
    public void setValue(double servings) {
        this.servings = servings;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get everything - yyyy,mm,dd,f,the food's name,servings
     * (slightly different than other implementations of Log)
     * @return {yyyy,mm,dd,f,the food's name,servings}
     */
    @Override
    public List<String> getAll() {
        List<String> all = new ArrayList<String>(6);
        String yyyy = String.format("%04d", date.getYear());
        String mm = String.format("%02d", date.getMonthValue());
        String dd = String.format("%02d", date.getDayOfMonth());
        all.add(yyyy);
        all.add(mm);
        all.add(dd);
        all.add("f");
        all.add(food.getName());
        all.add(Double.toString(servings));
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
