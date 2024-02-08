package skeleton.controller;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuNavListener implements ActionListener {

    private JPanel cards;

    public MenuNavListener(JPanel cards) {
        this.cards = cards;
    }

    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, e.getActionCommand());
    }
}