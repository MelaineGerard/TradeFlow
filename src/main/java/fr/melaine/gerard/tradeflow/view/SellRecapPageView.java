package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SellRecapPageView extends JFrame {

    private final JFrame parent;

    public SellRecapPageView(JFrame parent) {
        super("TradeFlow - Sell Recap");
        this.parent = parent;

        initPanel();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initPanel() {
        JPanel panel = new JPanel();

        MigLayout miglayout = new MigLayout(
                "hidemode 3",
                "[grow][fill][grow]",
                "[grow][fill][fill][fill][fill][grow]");

        panel.setLayout(miglayout);

        JLabel label = new JLabel("Récapitulatif de la transaction");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(48.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton backButton = new JButton("Retour à l'accueil");
        backButton.setFont(backButton.getFont().deriveFont(24.0f));

        panel.add(label, "cell 1 1");
        panel.add(backButton, "cell 1 4");

        backButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        this.setContentPane(panel);
    }
}
