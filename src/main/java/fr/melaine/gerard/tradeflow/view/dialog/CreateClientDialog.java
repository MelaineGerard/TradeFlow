package fr.melaine.gerard.tradeflow.view.dialog;

import fr.melaine.gerard.tradeflow.model.Client;
import fr.melaine.gerard.tradeflow.service.ClientService;
import fr.melaine.gerard.tradeflow.view.ManageClientsPageView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class CreateClientDialog extends JDialog {
    private final ManageClientsPageView parent;
    private final Client client;

    public CreateClientDialog(ManageClientsPageView parent, Client client) {
        super();
        this.parent = parent;
        this.client = client;

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
                "[grow][fill][fill][fill][fill][fill][fill][grow]");

        panel.setLayout(miglayout);

        JLabel nameLabel = new JLabel("Nom");
        JTextField nameField = new JTextField();

        JLabel addressLabel = new JLabel("Adresse");
        JTextField addressField = new JTextField();

        JLabel cityLabel = new JLabel("Ville");
        JTextField cityField = new JTextField();

        JButton createButton = new JButton("Créer");


        if (client != null) {
            nameField.setText(client.getName());
            addressField.setText(client.getAddress());
            cityField.setText(client.getCity());
        }


        panel.add(nameLabel, "cell 1 1");
        panel.add(nameField, "cell 1 2");
        panel.add(addressLabel, "cell 1 3");
        panel.add(addressField, "cell 1 4");
        panel.add(cityLabel, "cell 1 5");
        panel.add(cityField, "cell 1 6");
        panel.add(createButton, "cell 1 7");

        panel.setLayout(miglayout);

        this.setContentPane(panel);

        createButton.addActionListener(e -> {
            Client newClient = new Client(
                    0,
                    nameField.getText(),
                    addressField.getText(),
                    cityField.getText()
                );

            boolean isGood;

            if (this.client != null) {
                newClient.setId(this.client.getId());
                isGood = ClientService.editClient(newClient);
            } else {
                isGood = ClientService.createClient(newClient);
            }

            if (isGood) {
                this.setVisible(false);
                this.parent.setVisible(true);
                this.parent.refresh();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la création du client");
            }
        });
    }

}
