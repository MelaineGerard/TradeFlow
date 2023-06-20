package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fr.melaine.gerard.tradeflow.TradeFlow;

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
                "[fill][fill][grow][fill]");

        panel.setLayout(miglayout);

        JButton newUserButton = new JButton("Créer une prestation");
        JLabel usersLabel = new JLabel("Prestations");
        usersLabel.setFont(usersLabel.getFont().deriveFont(20.0f));
        
        JScrollPane prestationsScrollPane = new JScrollPane();
        JTable prestationsTable = new JTable();
        DefaultTableModel prestationsTableModel = new DefaultTableModel();
        prestationsTableModel.addColumn("Id");
        prestationsTableModel.addColumn("Nom");
        prestationsTableModel.addColumn("Prix");
        prestationsTableModel.addColumn("Actions");
        prestationsTable.setModel(prestationsTableModel);
        // ajout des prestations
        TradeFlow.getPrestations().forEach(prestation -> {
            prestationsTableModel.addRow(new Object[] {
                    prestation.getId(),
                    prestation.getName(),
                    prestation.getPrice(),
                    "Modifier | Supprimer"
            });
        });

        prestationsScrollPane.setViewportView(prestationsTable);

        JButton backButton = new JButton("Retour");

        // ajout sur la première ligne
        panel.add(newUserButton, "cell 1 0");
        panel.add(usersLabel, "cell 1 1");
        panel.add(prestationsScrollPane, "cell 1 2");
        panel.add(backButton, "cell 1 3");

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        this.setContentPane(panel);
    }
}
