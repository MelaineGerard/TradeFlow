package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ManagePrestationsPageView extends JFrame {
    private final JFrame parent;

    public ManagePrestationsPageView(JFrame parent) {
        super("TradeFlow - Gestion des prestations");
        this.parent = parent;

        initPanel();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initPanel() {
        JPanel panel = new JPanel();
        MigLayout miglayout = new MigLayout(
                "hidemode 3",
                "[grow][fill][grow]",
                "[grow][fill][grow]");

        panel.setLayout(miglayout);

        JButton newUserButton = new JButton("Créer une prestation");
        JLabel usersLabel = new JLabel("Prestations");
        usersLabel.setFont(usersLabel.getFont().deriveFont(20.0f));
        JButton backButton = new JButton("Retour");

        panel.add(newUserButton, "cell 1 0");
        panel.add(usersLabel, "cell 1 1");
        panel.add(backButton, "cell 1 2");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        this.setContentPane(panel);
    }
}
