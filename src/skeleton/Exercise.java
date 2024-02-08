package skeleton;

public interface Exercise {

    /**
     * Set the name of the exercise
     * @param name the name as a string to set
     * @return void
     */
    public void setName (String name);

     /**
     * Set the calories of the exercise
     * @param cal the amount of calories to set as a double
     * @return void
     */
    public void setCalories (double cal);    

    /**
     * Get the name of the exercise
     * @return The name of the exercise
     */
    public String getName ();

    /**
     * Get the calories of the exercise
     * @return The calories of the exercise as a double
     */
    public double getCalories ();

     /**
     * toString method for Exercises
     * @return Formatted string with info an exercise
     */
    public String toString();

}