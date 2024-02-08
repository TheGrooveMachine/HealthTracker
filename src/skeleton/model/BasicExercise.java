package skeleton.model;

import skeleton.Exercise;

public class BasicExercise implements Exercise {
    
    private String name;
    private double calories;

    public BasicExercise(String name, double calories){
        this.name = name;
        this.calories = calories;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setCalories(double calories){
        this.calories = calories;
    }

    @Override
    public String toString() {
        return name + ", " + calories;
    }
}
