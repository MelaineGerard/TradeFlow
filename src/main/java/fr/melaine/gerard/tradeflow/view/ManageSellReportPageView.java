package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ManageSellReportPageView extends JFrame {
    private final JFrame parent;

    public ManageSellReportPageView(JFrame parent) {
        super("TradeFlow - Gestion des rapports de ventes");
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

        JLabel usersLabel = new JLabel("Rapports de vente");
        usersLabel.setFont(usersLabel.getFont().deriveFont(20.0f));
        JButton backButton = new JButton("Retour");

        panel.add(usersLabel, "cell 1 1");
        panel.add(backButton, "cell 1 2");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        this.setContentPane(panel);
    }
}
