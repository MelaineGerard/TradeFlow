package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ManageHomePageView extends JFrame {
    private final JFrame parent;

    public ManageHomePageView(JFrame parent) {
        super("TradeFlow - Manage Home Page");
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
                "[grow][fill][fill][fill][fill][fill][fill][grow]");

        panel.setLayout(miglayout);

        JLabel label = new JLabel("Que veux-tu faire, " + "admin" + " !");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(48.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JButton manageUserButton = new JButton("Gestion des utilisateurs");
        manageUserButton.setFont(manageUserButton.getFont().deriveFont(24.0f));

        JButton manageSellReportButton = new JButton("Gestion des rapports de vente");
        manageSellReportButton.setFont(manageUserButton.getFont().deriveFont(24.0f));

        JButton managePrestationButton = new JButton("Gestion des prestations");
        managePrestationButton.setFont(manageUserButton.getFont().deriveFont(24.0f));

        JButton manageClientsButton = new JButton("Gestion des clients");
        manageClientsButton.setFont(manageUserButton.getFont().deriveFont(24.0f));

        JButton backButton = new JButton("Retour à l'accueil");
        backButton.setFont(manageUserButton.getFont().deriveFont(24.0f));

        panel.add(label, "cell 1 1");
        panel.add(manageUserButton, "cell 1 2");
        panel.add(manageSellReportButton, "cell 1 3");
        panel.add(managePrestationButton, "cell 1 4");
        panel.add(manageClientsButton, "cell 1 5");
        panel.add(backButton, "cell 1 6");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        manageUserButton.addActionListener(e -> {
            this.setVisible(false);
            new ManageUsersPageView(this);
        });

        manageClientsButton.addActionListener(e -> {
            this.setVisible(false);
            new ManageClientsPageView(this);
        });

        managePrestationButton.addActionListener(e -> {
            this.setVisible(false);
            new ManagePrestationsPageView(this);
        });

        manageSellReportButton.addActionListener(e -> {
            this.setVisible(false);
            new ManageSellReportPageView(this);
        });

        this.setContentPane(panel);
    }
}
