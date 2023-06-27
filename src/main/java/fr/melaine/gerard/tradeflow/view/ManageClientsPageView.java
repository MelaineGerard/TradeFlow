package fr.melaine.gerard.tradeflow.view;

import fr.melaine.gerard.tradeflow.model.Client;
import fr.melaine.gerard.tradeflow.service.ClientService;
import fr.melaine.gerard.tradeflow.service.LoadApiService;
import fr.melaine.gerard.tradeflow.view.dialog.CreateClientDialog;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fr.melaine.gerard.tradeflow.TradeFlow;

import java.io.IOException;

public class ManageClientsPageView extends JFrame {
    private final JFrame parent;
    private JTable clientsTable;

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
        clientsTable = new JTable();


        clientsScrollPane.setViewportView(clientsTable);

        JButton deleteButton = new JButton("Supprimer");
        JButton editButton = new JButton("Modifier");
        JButton backButton = new JButton("Retour");

        // ajout sur la première ligne
        panel.add(newUserButton, "cell 1 0");
        panel.add(usersLabel, "cell 1 1");
        panel.add(clientsScrollPane, "cell 1 2");
        panel.add(deleteButton, "cell 1 3");
        panel.add(editButton, "cell 1 4");
        panel.add(backButton, "cell 1 5");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = clientsTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) clientsTable.getValueAt(selectedRow, 0);
                ClientService.deleteClient(id);
                this.refresh();
            }
        });

        newUserButton.addActionListener(e -> {
            new CreateClientDialog(this, null);
            this.setVisible(false);
        });

        editButton.addActionListener(e -> {
            int selectedRow = clientsTable.getSelectedRow();

            if (selectedRow != -1) {
                int userId = (int) clientsTable.getValueAt(selectedRow, 0);
                String name = (String) clientsTable.getValueAt(selectedRow, 1);
                String address = (String) clientsTable.getValueAt(selectedRow, 2);
                String city = (String) clientsTable.getValueAt(selectedRow, 3);

                Client client = new Client(
                        userId,
                        name,
                        address,
                        city
                );
                new CreateClientDialog(this, client);
                this.setVisible(false);
            }
        });

        this.setContentPane(panel);
    }


    public void refresh() {
        try {
            LoadApiService.getInstance().loadClients();
            DefaultTableModel clientsTableModel = new DefaultTableModel();
            clientsTableModel.addColumn("Id");
            clientsTableModel.addColumn("Nom");
            clientsTableModel.addColumn("Adresse");
            clientsTableModel.addColumn("Ville");
            clientsTable.setModel(clientsTableModel);
            // ajout des clients
            TradeFlow.getClients().forEach(client -> clientsTableModel.addRow(new Object[] {
                    client.getId(),
                    client.getName(),
                    client.getAddress(),
                    client.getCity()
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
