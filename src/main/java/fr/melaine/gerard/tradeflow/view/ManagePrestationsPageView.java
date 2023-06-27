package fr.melaine.gerard.tradeflow.view;

import fr.melaine.gerard.tradeflow.model.Prestation;
import fr.melaine.gerard.tradeflow.service.LoadApiService;
import fr.melaine.gerard.tradeflow.service.PrestationService;
import fr.melaine.gerard.tradeflow.view.dialog.CreatePrestationDialog;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fr.melaine.gerard.tradeflow.TradeFlow;

import java.io.IOException;

public class ManagePrestationsPageView extends JFrame {
    private final JFrame parent;
    private JTable prestationsTable;

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
                "[fill][fill][grow][fill]");

        panel.setLayout(miglayout);

        JButton newUserButton = new JButton("Créer une prestation");
        JLabel usersLabel = new JLabel("Prestations");
        usersLabel.setFont(usersLabel.getFont().deriveFont(20.0f));
        
        JScrollPane prestationsScrollPane = new JScrollPane();
        prestationsTable = new JTable();
        refresh();

        prestationsScrollPane.setViewportView(prestationsTable);

        JButton editButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");
        JButton backButton = new JButton("Retour");

        panel.add(newUserButton, "cell 1 0");
        panel.add(usersLabel, "cell 1 1");
        panel.add(prestationsScrollPane, "cell 1 2");
        panel.add(editButton, "cell 1 3");
        panel.add(deleteButton, "cell 1 4");
        panel.add(backButton, "cell 1 5");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });


        deleteButton.addActionListener(e -> {
            int selectedRow = prestationsTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) prestationsTable.getValueAt(selectedRow, 0);
                PrestationService.deletePrestation(id);
                this.refresh();
            }
        });

        newUserButton.addActionListener(e -> {
            new CreatePrestationDialog(this, null);
            this.setVisible(false);
        });

        editButton.addActionListener(e -> {
            int selectedRow = prestationsTable.getSelectedRow();

            if (selectedRow != -1) {
                int userId = (int) prestationsTable.getValueAt(selectedRow, 0);
                String name = (String) prestationsTable.getValueAt(selectedRow, 1);
                float price = (float) prestationsTable.getValueAt(selectedRow, 2);

                Prestation prestation = new Prestation(
                        userId,
                        name,
                        price
                );
                new CreatePrestationDialog(this, prestation);
                this.setVisible(false);
            }
        });

        this.setContentPane(panel);
    }

    public void refresh() {
        try {
            LoadApiService.getInstance().loadPrestations();
            DefaultTableModel prestationsTableModel = new DefaultTableModel();
            prestationsTableModel.addColumn("Id");
            prestationsTableModel.addColumn("Nom");
            prestationsTableModel.addColumn("Prix");
            prestationsTable.setModel(prestationsTableModel);
            // ajout des prestations
            TradeFlow.getPrestations().forEach(prestation -> prestationsTableModel.addRow(new Object[] {
                    prestation.getId(),
                    prestation.getName(),
                    prestation.getPrice()
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
