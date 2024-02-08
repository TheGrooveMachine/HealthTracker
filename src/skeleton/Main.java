package skeleton;

import javax.swing.JFrame;

import skeleton.model.CSV;
import skeleton.model.DailyLog;
import skeleton.model.Data;
import skeleton.model.ExerciseCollection;
import skeleton.model.FoodCollection;
import skeleton.view.CLI;
import skeleton.view.MainNavPanel;

public class Main {
   public static void main(String[] args) {
      Data data = new CSV();
      FoodCollection fc = new FoodCollection(data.loadFoods());
      ExerciseCollection ec = new ExerciseCollection(data.loadExercises());
      DailyLog dl = new DailyLog();
      // Food[] csvData = data.loadFoods();
      // System.out.println(data);
      // System.out.println("hello");
      // for(int i = 0; i < csvData.length; i++){
      // System.out.println(csvData[i]);
      // }

      // Create and set up the window.
      JFrame frame = new JFrame("Wellness Manager");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // Create and set up the content pane.
      MainNavPanel nav = new MainNavPanel(fc, ec, data);
      nav.addComponentToPane(frame.getContentPane());

      // Display the window.
      frame.pack();
      frame.setVisible(true);

      CLI cli = new CLI(fc, dl, data);
      cli.mainMenu();
   }
}
