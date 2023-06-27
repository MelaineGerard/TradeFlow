package fr.melaine.gerard.tradeflow.view;

import fr.melaine.gerard.tradeflow.model.User;
import fr.melaine.gerard.tradeflow.service.UserService;
import net.miginfocom.swing.MigLayout;

import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import fr.melaine.gerard.tradeflow.TradeFlow;
import fr.melaine.gerard.tradeflow.service.LoadApiService;
import fr.melaine.gerard.tradeflow.view.dialog.CreateUserDialog;

public class ManageUsersPageView extends JFrame {
    private final JFrame parent;
    private JTable usersTable;
    public ManageUsersPageView(JFrame parent) {
        super("TradeFlow - Gestion des utilisateurs");
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
                "[fill][fill][grow][fill][fill][fill]");

        panel.setLayout(miglayout);

        JButton newUserButton = new JButton("Créer un utilisateur");
        JLabel usersLabel = new JLabel("Utilisateurs");
        usersLabel.setFont(usersLabel.getFont().deriveFont(20.0f));
        
        JScrollPane usersScrollPane = new JScrollPane();
        usersTable = new JTable();
        
        refresh();

        usersScrollPane.setViewportView(usersTable);

        JButton backButton = new JButton("Retour");
        JButton deleteButton = new JButton("Supprimer");
        JButton editButton = new JButton("Editer");

        // ajout sur la première ligne
        panel.add(newUserButton, "cell 1 0");
        panel.add(usersLabel, "cell 1 1");
        panel.add(usersScrollPane, "cell 1 2");
        panel.add(deleteButton, "cell 1 3");
        panel.add(editButton, "cell 1 4");
        panel.add(backButton, "cell 1 5");

        deleteButton.addActionListener(e -> {
            int selectedRow = usersTable.getSelectedRow();
            if(selectedRow != -1) {
                int userId = (int) usersTable.getValueAt(selectedRow, 0);
                if (UserService.deleteUser(userId)) {
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(this, "Impossible de supprimer l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.parent.setVisible(true);
        });

        newUserButton.addActionListener(e -> {
            new CreateUserDialog(this, null);
            this.setVisible(false);
        });

        editButton.addActionListener(e -> {
            int selectedRow = usersTable.getSelectedRow();

            if (selectedRow != -1) {
                int userId = (int) usersTable.getValueAt(selectedRow, 0);
                String name = (String) usersTable.getValueAt(selectedRow, 1);
                String username = (String) usersTable.getValueAt(selectedRow, 2);
                String role = (String) usersTable.getValueAt(selectedRow, 3);

                User user = new User(userId, name, username, null, role);
                new CreateUserDialog(this, user);
                this.setVisible(false);
            }
        });


        this.setContentPane(panel);
    }

    public void refresh() {
        try {
            LoadApiService.getInstance().loadUsers();
            DefaultTableModel usersTableModel = new DefaultTableModel();
            usersTableModel.addColumn("Id");
            usersTableModel.addColumn("Nom");
            usersTableModel.addColumn("Pseudo");
            usersTableModel.addColumn("Rôle");
            usersTable.setModel(usersTableModel);

            TradeFlow.getUsers().forEach(user -> {
                usersTableModel.addRow(new Object[] {
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getRole()
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
