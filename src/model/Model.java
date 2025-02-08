package model;

import java.util.*;

public class Model {
    private final List<Map<String, String>> inventory;
    private final Map<String, Integer> statistics;

    public Model() {
        inventory = new ArrayList<>();
        statistics = new HashMap<>();
        statistics.put("Food_Accepted", 0);
        statistics.put("Food_Rejected", 0);
        statistics.put("Electronics_Accepted", 0);
        statistics.put("Electronics_Rejected", 0);
        statistics.put("Clothing_Accepted", 0);
        statistics.put("Clothing_Rejected", 0);
    }
    //เช็คเงื่อนไข
    public boolean addProduct(String id, String type, String expiry, String condition) {
        for (Map<String, String> product : inventory) {
            if (product.get("Product_ID").equals(id)) {
                return false; // Duplicate ID
            }
        }

        if (type.equals("Food") && expiry != null && !isValidExpiryDate(expiry)) {
            statistics.put("Food_Rejected", statistics.get("Food_Rejected") + 1);
            return false;
        }

        if (type.equals("Electronics") && (condition.equals("Damaged")) ||(condition.equals("Needs Further Inspection"))) {
            statistics.put("Electronics_Rejected", statistics.get("Electronics_Rejected") + 1);
            return false;
        }

        

        if (type.equals("Clothing") && condition.equals("Damaged")) {
            statistics.put("Clothing_Rejected", statistics.get("Clothing_Rejected") + 1);
            return false;
        }

        Map<String, String> product = new HashMap<>();
        product.put("Product_ID", id);
        product.put("Category", type);
        product.put("Expiry_Date", expiry);
        product.put("Condition", condition);
        inventory.add(product);

        statistics.put(type + "_Accepted", statistics.get(type + "_Accepted") + 1);
        return true;
    }
    //เช็ควันที่ปัจุบัน หากเกินคือหมดอายุ
    private boolean isValidExpiryDate(String expiry) {
        String[] parts = expiry.split("-");
        Calendar expiryDate = new GregorianCalendar(
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]) - 1,
            Integer.parseInt(parts[2])
        );
        Calendar today = Calendar.getInstance();
        return !expiryDate.before(today);
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }
}
