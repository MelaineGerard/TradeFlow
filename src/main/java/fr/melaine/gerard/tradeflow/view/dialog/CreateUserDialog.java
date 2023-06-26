package fr.melaine.gerard.tradeflow.view.dialog;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.melaine.gerard.tradeflow.model.User;
import fr.melaine.gerard.tradeflow.service.UserService;
import fr.melaine.gerard.tradeflow.view.ManageUsersPageView;
import net.miginfocom.swing.MigLayout;

public class CreateUserDialog extends JDialog {
    private final ManageUsersPageView parent;

    public CreateUserDialog(ManageUsersPageView parent) {
        super();
        this.parent = parent;

        initPanel();
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initPanel() {
        JPanel panel = new JPanel();

        MigLayout miglayout = new MigLayout(
                "hidemode 3",
                "[grow][fill][grow]",
                "[grow][fill][fill][fill][fill][fill][fill][fill][fill][grow]");

        panel.setLayout(miglayout);

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField fullNameField = new JTextField();
        JComboBox<String> roleComboBox = new JComboBox<String>();
        JButton createButton = new JButton("Créer");
        
        roleComboBox.addItem("ROLE_ADMIN");
        roleComboBox.addItem("ROLE_USER");

        panel.add(new JLabel("Nom d'utilisateur"), "cell 1 1");
        panel.add(usernameField, "cell 1 2");
        panel.add(new JLabel("Mot de passe"), "cell 1 3");
        panel.add(passwordField, "cell 1 4");
        panel.add(new JLabel("Nom complet"), "cell 1 5");
        panel.add(fullNameField, "cell 1 6");
        panel.add(new JLabel("Rôle"), "cell 1 7");
        panel.add(roleComboBox, "cell 1 8");
        panel.add(createButton, "cell 1 9");

        panel.setLayout(miglayout);

        this.setContentPane(panel);

        createButton.addActionListener(e -> {
            User user = new User(
                0, 
                usernameField.getText(),
                new String(passwordField.getPassword()),
                fullNameField.getText(),
                roleComboBox.getSelectedItem().toString()
                );

            if (UserService.createUser(user)) {

                this.setVisible(false);
                this.parent.setVisible(true);
                this.parent.refresh();
            }

        });
    }

}
