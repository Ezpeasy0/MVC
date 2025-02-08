package view;  

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.*;

public class View extends JFrame {
    private final JTextField idField;
    private final JTextField expiryField;
    private final JComboBox<String> typeBox;
    private final JComboBox<String> conditionBox;
    private final JButton submitButton;
    private final JTextArea outputArea;

    public View() {
        setTitle("Product Import System");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));
        

        add(new JLabel("Product ID (6 digits, first cannot be 0):"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Product Type:"));
        typeBox = new JComboBox<>(new String[]{"Food", "Electronics", "Clothing"});
        add(typeBox);

        add(new JLabel("Expiry Date (for food only) Set (yyyy-mm-dd) :"));
        expiryField = new JTextField();
        add(expiryField);

        add(new JLabel("Product Condition:"));
        conditionBox = new JComboBox<>(new String[]{"Normal", "Damaged", "Needs Further Inspection"});
        add(conditionBox);

        submitButton = new JButton("Save Product");
        add(submitButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        setVisible(true);
    }

    public String getProductId() {
        return idField.getText();
    }

    public String getProductType() {
        return (String) typeBox.getSelectedItem();
    }

    public String getExpiryDate() {
        return expiryField.getText();
    }

    public String getCondition() {
        return (String) conditionBox.getSelectedItem();
    }

    public void setSubmitAction(ActionListener action) {
        submitButton.addActionListener(action);
    }

    public void showMessage(String message) {
        outputArea.append(message + "\n");
    }

    public void updateStatistics(Map<String, Integer> stats) {
        outputArea.append("\n--- Product Receiving Statistics ---\n");
        for (String key : stats.keySet()) {
            outputArea.append(key + ": " + stats.get(key) + "\n");
        }
    }
}
