package controller;  

import java.awt.event.ActionEvent;
import model.Model;
import view.View;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.view.setSubmitAction((ActionEvent e) -> {
            handleProductEntry();
        });
    }

    private void handleProductEntry() {
        String id = view.getProductId();
        String type = view.getProductType();
        String expiry = type.equals("Food") ? view.getExpiryDate() : null;
        String condition = view.getCondition();

        if (!id.matches("[1-9]\\d{5}")) {
            view.showMessage("❌ Invalid product ID!");
            return;
        }

        boolean success = model.addProduct(id, type, expiry, condition);
        view.showMessage(success ? "✅ Product saved successfully!" : "❌ Product entry denied!");

        view.updateStatistics(model.getStatistics());
    }
}
