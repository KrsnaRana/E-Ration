package org.harsh.Model;

public class Product {
    private int product_id;
    private String product_name;
    private double product_price;
    private int product_quantity;

    public Product() {}

    public Product(int product_id, String product_name, double product_price, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    public Product( String product_name, double product_price, int product_quantity) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    public int getProductId() {
        return product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public double getProductPrice() {
        return product_price;
    }

    public int getProductStock() {
        return product_quantity;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public void setProductPrice(double product_price) {
        this.product_price = product_price;
    }

    public void setProductStock(int product_quantity) {
        this.product_quantity = product_quantity;
    }
}