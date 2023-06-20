package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SellPageView extends JFrame {

    private final JFrame parent;
    private SellRecapPageView sellRecapPageView;

    public SellPageView(JFrame parent) {
        super("TradeFlow - Page de vente");
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

        JLabel label = new JLabel("Vendre");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(48.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton sellButton = new JButton("Passer à la vente");
        sellButton.setFont(sellButton.getFont().deriveFont(24.0f));

        JButton backButton = new JButton("Retour à l'accueil");
        backButton.setFont(backButton.getFont().deriveFont(24.0f));

        panel.add(label, "cell 1 1");
        panel.add(sellButton, "cell 1 3");
        panel.add(backButton, "cell 1 4");

        sellButton.addActionListener(e -> {
            if (sellRecapPageView == null) {
                this.sellRecapPageView = new SellRecapPageView(this);
            }
            sellRecapPageView.setVisible(true);
            this.setVisible(false);
        });

        backButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        this.setContentPane(panel);
    }
}
