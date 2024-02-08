package skeleton;


//The purpose of this class is to receive updates from the main class and display them in a text-ui format.
public interface UI {

    //Method to receive the updates from the Main function to display in the text UI

    //Have updates in the parameters
    public void sendUpdates();

    //Add a recipe 
    public void addRecipe();
    
    //Get the data from the CSV
    public void getData();
     
    //Delete data from the CSV
    public void deleteData();
     
    //Save any changes made to the CSV file
    public void saveChanges();
    
    public void userPrompt();
    
}
