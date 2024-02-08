package skeleton.view;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import skeleton.controller.MenuNavListener;
import skeleton.model.FoodCollection;

public class FoodPanel extends JPanel {

    JPanel cards; // a panel that uses CardLayout

    final static String ADD = "Add a food";
    final static String REMOVE = "Remove a food";
    final static String UPDATE = "Update a food";
    final static String LIST = "Show food list";

    private FoodCollection fc;

    public FoodPanel(FoodCollection fc) {

        this.fc=fc;

        // Set layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        // Create panel to hold food nav buttons
        JPanel buttonPanel = new JPanel();

        // Create the "cards".
        JPanel card1 = new AddFoodMenu(fc);

        JPanel card2 = new RemoveFoodMenu(fc);

        JPanel card3 = new UpdateFoodMenu(fc);

        //JPanel card4 = new ExercisePanel();

        // Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());

        // Add the cards
        cards.add(card1, ADD);
        cards.add(card2, REMOVE);
        cards.add(card3, UPDATE);
       // cards.add(card4, LIST);

        // Create the buttons for main nav and add them
        buttonPanel.add(menuButton(ADD));
        buttonPanel.add(menuButton(REMOVE));
        buttonPanel.add(menuButton(UPDATE));
        //buttonPanel.add(menuButton(LIST));

        // Add the created panes to the frame and set the layout
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 0;
        this.add(buttonPanel, cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = 1;
        this.add(cards, cons);
    }

    private JButton menuButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new MenuNavListener(cards));
        return button;
    }
}
