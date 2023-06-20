package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fr.melaine.gerard.tradeflow.TradeFlow;

public class ManageClientsPageView extends JFrame {
    private final JFrame parent;

    public ManageClientsPageView(JFrame parent) {
        super("TradeFlow - Gestion des clients");
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
                "[fill][fill][grow][fill]");

        panel.setLayout(miglayout);

        JButton newUserButton = new JButton("Créer un client");
        JLabel usersLabel = new JLabel("Clients");
        usersLabel.setFont(usersLabel.getFont().deriveFont(20.0f));

        JScrollPane clientsScrollPane = new JScrollPane();
        JTable clientsTable = new JTable();
        DefaultTableModel clientsTableModel = new DefaultTableModel();
        clientsTableModel.addColumn("Id");
        clientsTableModel.addColumn("Nom");
        clientsTableModel.addColumn("Adresse");
        clientsTableModel.addColumn("Ville");
        clientsTableModel.addColumn("Actions");
        clientsTable.setModel(clientsTableModel);
        // ajout des clients
        TradeFlow.getClients().forEach(client -> {
            clientsTableModel.addRow(new Object[] {
                    client.getId(),
                    client.getName(),
                    client.getAddress(),
                    client.getCity(),
                    "Modifier | Supprimer"
            });
        });

        clientsScrollPane.setViewportView(clientsTable);

        JButton backButton = new JButton("Retour");

        // ajout sur la première ligne
        panel.add(newUserButton, "cell 1 0");
        panel.add(usersLabel, "cell 1 1");
        panel.add(clientsScrollPane, "cell 1 2");
        panel.add(backButton, "cell 1 3");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        this.setContentPane(panel);
    }
}
