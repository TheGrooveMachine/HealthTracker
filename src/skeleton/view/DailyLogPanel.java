package skeleton.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import skeleton.controller.MenuNavListener;



public class DailyLogPanel extends JPanel {

    JPanel cards;

    final static String ADD = "Add a daily log";
    final static String DELETE = "Delete a daily log";
    final static String UPDATE = "Update a daily log";
    final static String LIST = "Show a daily log";

    public DailyLogPanel() {

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        JPanel buttonPanel = new JPanel();

        JPanel card1 = new AddDailyLogMenu();

        JPanel card2 = new RemoveDailyLogMenu();

        JPanel card3 = new UpdateDailyLogMenu();

        //JPanel card4 = new ExercisePanel();


        // Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());

        // Add the cards
        cards.add(card1, ADD);
        cards.add(card2, DELETE);
        cards.add(card3, UPDATE);
        //cards.add(card4, LIST);

        // Create the buttons for main nav and add them
        buttonPanel.add(menuButton(ADD));
        buttonPanel.add(menuButton(DELETE));
        buttonPanel.add(menuButton(UPDATE));
        buttonPanel.add(menuButton(LIST));

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