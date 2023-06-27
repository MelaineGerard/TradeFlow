package fr.melaine.gerard.tradeflow.view.dialog;

import fr.melaine.gerard.tradeflow.model.Prestation;
import fr.melaine.gerard.tradeflow.service.PrestationService;
import fr.melaine.gerard.tradeflow.view.ManagePrestationsPageView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class CreatePrestationDialog extends JDialog {
    private final ManagePrestationsPageView parent;
    private final Prestation prestation;

    public CreatePrestationDialog(ManagePrestationsPageView parent, Prestation prestation) {
        super();
        this.parent = parent;
        this.prestation = prestation;

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

        JLabel priceLabel = new JLabel("Prix");
        JTextField priceField = new JTextField();

        JButton createButton = new JButton("Créer");


        if (prestation != null) {
            nameField.setText(prestation.getName());
            priceField.setText(String.valueOf(prestation.getPrice()));
        }


        panel.add(nameLabel, "cell 1 1");
        panel.add(nameField, "cell 1 2");
        panel.add(priceLabel, "cell 1 3");
        panel.add(priceField, "cell 1 4");
        panel.add(createButton, "cell 1 5");

        panel.setLayout(miglayout);

        this.setContentPane(panel);

        createButton.addActionListener(e -> {
            Prestation newPrestation = new Prestation(
                    0,
                    nameField.getText(),
                    Float.parseFloat(priceField.getText())
                );

            boolean isGood;

            if (this.prestation != null) {
                newPrestation.setId(this.prestation.getId());
                isGood = PrestationService.editPrestation(newPrestation);
            } else {
                isGood = PrestationService.createPrestation(newPrestation);
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
