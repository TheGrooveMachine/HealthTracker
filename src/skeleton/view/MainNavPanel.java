package skeleton.view;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Observer;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JPanel;

import skeleton.controller.MenuNavListener;
import skeleton.model.Data;
import skeleton.model.ExerciseCollection;
import skeleton.model.FoodCollection;

public class MainNavPanel implements Observer {
    JPanel cards; // a panel that uses CardLayout
    final static String HOME = "Main";
    final static String FOODMENU = "Food menu";
    final static String DAILYLOGMENU = "Daily log menu";
    final static String EXERCISEMENU = "Exercise Menu";

    private FoodCollection fc;
    private ExerciseCollection ec;
    private Data type;

    private JPanel pane;

    public MainNavPanel(FoodCollection fc, ExerciseCollection ec, Data type) {
        this.fc = fc;
        this.ec = ec;
        this.type = type;

        fc.addObserver(this);
        ec.addObserver(this);
    }

    public void addComponentToPane(Container pane) {

        this.pane = (JPanel) pane;

        // Create panel to hold main nav buttons
        JPanel buttonPanel = new JPanel();

        // Create the "cards".
        JPanel card1 = new MainMenuPanel(fc, ec, type);

        JPanel card2 = new FoodPanel(fc);

        JPanel card3 = new DailyLogPanel();

        JPanel card4 = new ExercisePanel(ec);

        // Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());

        // Add the cards
        cards.add(card1, HOME);
        cards.add(card2, FOODMENU);
        cards.add(card3, DAILYLOGMENU);
        cards.add(card4, EXERCISEMENU);

        // Create the buttons for main nav and add them
        buttonPanel.add(menuButton(HOME));
        buttonPanel.add(menuButton(FOODMENU));
        buttonPanel.add(menuButton(DAILYLOGMENU));
        buttonPanel.add(menuButton(EXERCISEMENU));

        // Add the created panes to the frame
        pane.add(buttonPanel, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    private JButton menuButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new MenuNavListener(cards));
        return button;
    }

    public void update(Observable obs, Object o) {
        pane.removeAll();
        this.addComponentToPane(pane);
        pane.revalidate();
        
    }
}
