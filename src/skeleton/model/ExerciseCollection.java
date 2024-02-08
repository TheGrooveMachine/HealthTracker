package skeleton.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import skeleton.Exercise;

public class ExerciseCollection extends Observable{

    private List<Exercise> exerciseList;

    public ExerciseCollection() {
        exerciseList = new ArrayList<Exercise>();
    }
    
    public ExerciseCollection(Exercise[] exercises) {
        exerciseList = new ArrayList<Exercise>(Arrays.asList(exercises));
    }

    /**
     * Search for a given name in the exercise list
     * 
     * @param name the name to search for as a string
     * @return the exercise if found or null if not
     */
    public Exercise find(String name) {
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().equals(name)) {
                return exercise;
            }
        }
        return null;
    }

    public List<Exercise> getExerciseList (){
        return exerciseList;
    }

    /**
     * Add a exercise to the exercise list
     * Also notify observers about the change to exerciseList
     * 
     * @param name     the name of the exercise as a string
     * @param calories the amount of calories as a double
     * @return void
     */
    public void addExercise(String name, double calories) {
        if (exerciseList.add(new BasicExercise(name, calories))) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Remove a exercise from the exercise list
     * 
     * @param name the name of the exercise as a string
     */
    public void removeExercise(String name) {
        Exercise exercise = this.find(name);
        if (exerciseList.remove(exercise)) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * save exercise collection to a data file
     * 
     * @return void
     */
    public void saveToFile(Data type) {
        type.saveExercises(exerciseList.toArray(new Exercise[exerciseList.size()]));
    }

    /**
     * toString method for ExerciseCollection
     * 
     * @return Formatted string with info on all exercises
     */
    @Override
    public String toString() {
        int len = exerciseList.size();
        String ret = "";
        for (int i = 0; i < len; i++) {
            ret += exerciseList.get(i).toString();
            ret += "\n";
        }

        return ret;
    }
}