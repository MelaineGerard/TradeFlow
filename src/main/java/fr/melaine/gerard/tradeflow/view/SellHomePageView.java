package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import fr.melaine.gerard.tradeflow.TradeFlow;

public class SellHomePageView extends JFrame {

    private final JFrame parent;
    private DaySellReportPageView daySellReportPageView;
    private SellPageView sellPageView;

    public SellHomePageView(JFrame parent) {
        super("TradeFlow - Page d'accueil de vente");
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

        JLabel label = new JLabel("Que veux-tu faire, " + TradeFlow.getUser().getName() + " !");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(30.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton sellButton = new JButton("Vendre");
        sellButton.setFont(sellButton.getFont().deriveFont(24.0f));

        JButton generateSellerRapportButton = new JButton("Générer ton rapport de vente");
        generateSellerRapportButton.setFont(sellButton.getFont().deriveFont(24.0f));

        JButton backButton = new JButton("Retour à l'accueil");
        backButton.setFont(sellButton.getFont().deriveFont(24.0f));

        panel.add(label, "cell 1 1");
        panel.add(sellButton, "cell 1 2");
        panel.add(generateSellerRapportButton, "cell 1 3");
        panel.add(backButton, "cell 1 4");

        sellButton.addActionListener(e -> {
            if (this.sellPageView == null) {
                this.sellPageView = new SellPageView(this);
            }
            this.sellPageView.setVisible(true);
            this.setVisible(false);
        });

        generateSellerRapportButton.addActionListener(e -> {
            if (this.daySellReportPageView == null) {
                this.daySellReportPageView = new DaySellReportPageView(this);
            }
            this.daySellReportPageView.setVisible(true);
            this.setVisible(false);
        });

        backButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        this.setContentPane(panel);
    }
}
