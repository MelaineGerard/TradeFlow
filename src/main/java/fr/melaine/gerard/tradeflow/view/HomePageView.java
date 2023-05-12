package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class HomePageView extends JFrame {
    JPanel panel;
    JLabel label;
    JButton sellButton;
    JButton manageButton;
    JButton disconnectButton;

    JFrame parent;

    public HomePageView(JFrame parent) {
        super("TradeFlow - Home");
        this.parent = parent;
        initPanel();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initPanel() {
        panel = new JPanel();

        MigLayout miglayout = new MigLayout(
                "hidemode 3",
                "[grow][fill][grow]",
                "[grow][fill][fill][fill][fill][grow]");

        panel.setLayout(miglayout);

        label = new JLabel("Bienvenue sur TradeFlow, " + "admin" + " !");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(48.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        sellButton = new JButton("Vendre");
        sellButton.setFont(sellButton.getFont().deriveFont(24.0f));

        manageButton = new JButton("Gérer");
        manageButton.setFont(sellButton.getFont().deriveFont(24.0f));

        disconnectButton = new JButton("Se déconnecter");
        disconnectButton.setFont(sellButton.getFont().deriveFont(24.0f));

        panel.add(label, "cell 1 1");
        panel.add(sellButton, "cell 1 2");
        panel.add(manageButton, "cell 1 3");
        panel.add(disconnectButton, "cell 1 4");

        disconnectButton.addActionListener(e -> {
            parent.setVisible(true);
            this.dispose();
        });

        this.setContentPane(panel);
    }
}
