package skeleton.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import skeleton.controller.MenuNavListener;
import skeleton.model.ExerciseCollection;

public class ExercisePanel extends JPanel {

    JPanel cards; // a panel that uses CardLayout

    final static String ADD = "Add Exercise";
    final static String REMOVE = "Remove Exercise";
    final static String LIST = "Show Exercise";

    private ExerciseCollection ec;

    public ExercisePanel(ExerciseCollection ec) {
        this.ec = ec;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints cons = new GridBagConstraints();

        JPanel buttonPanel = new JPanel();

        JPanel card1 = new AddExerciseMenu(ec);

        JPanel card2 = new RemoveExerciseMenu(ec);

        JPanel card3 = new UpdateExerciseMenu(ec);

        // Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());

        // Add the cards
        cards.add(card1, ADD);
        cards.add(card2, REMOVE);
        cards.add(card3, "Update Exercise");

        buttonPanel.add(menuButton(ADD));
        buttonPanel.add(menuButton(REMOVE));
        buttonPanel.add(menuButton("Update Exercise"));

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