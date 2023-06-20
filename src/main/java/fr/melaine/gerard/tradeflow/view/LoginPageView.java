package fr.melaine.gerard.tradeflow.view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class LoginPageView extends JFrame {
    JPanel panel;
    JLabel label;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton quitButton;
    HomePageView homePageView;

    public LoginPageView() {
        super("TradeFlow - Login");

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
                "[grow][fill][fill][fill][fill][fill][grow]");

        panel.setLayout(miglayout);

        label = new JLabel("Bienvenue sur TradeFlow");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(48.0f));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        usernameField = new JTextField();
        usernameField.setFont(usernameField.getFont().deriveFont(24.0f));
        usernameField.setBorder(BorderFactory.createTitledBorder("Nom d'utilisateur"));

        passwordField = new JPasswordField();
        passwordField.setFont(passwordField.getFont().deriveFont(24.0f));
        passwordField.setBorder(BorderFactory.createTitledBorder("Mot de passe"));

        loginButton = new JButton("Se connecter");
        loginButton.setFont(loginButton.getFont().deriveFont(24.0f));
        loginButton.addActionListener(e -> {
            if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("admin")) {
                JOptionPane.showMessageDialog(this, "Bienvenue " + usernameField.getText(), "Bienvenue", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                if (homePageView == null) {
                    homePageView = new HomePageView(this);
                } else {
                    homePageView.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mauvais identifiants", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        quitButton = new JButton("Quitter");
        quitButton.setFont(quitButton.getFont().deriveFont(24.0f));
        quitButton.addActionListener(e -> System.exit(0));

        panel.add(label, "cell 1 1");
        panel.add(usernameField, "cell 1 2");
        panel.add(passwordField, "cell 1 3");
        panel.add(loginButton, "cell 1 4");
        panel.add(quitButton, "cell 1 5");
        this.setContentPane(panel);
    }
}
