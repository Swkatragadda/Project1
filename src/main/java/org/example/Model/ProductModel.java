package org.example.Model;


import java.util.Objects;

public class ProductModel {
    private long ProductID;
    private String ProductName;
    private double Price;
    private String SellerName;


    public ProductModel(long productID, String productName, double price, String sellerName) {
        ProductID = productID;
        ProductName = productName;
        Price = price;
        SellerName = sellerName;
    }

    public ProductModel(){

    }

    public long getProductID() {
        return ProductID;
    }

    public void setProductID(long productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "ProductID=" + ProductID +
                ", ProductName='" + ProductName + '\'' +
                ", Price=" + Price +
                ", SellerName='" + SellerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return ProductID == that.ProductID && Double.compare(Price, that.Price) == 0 && Objects.equals(ProductName, that.ProductName) && Objects.equals(SellerName, that.SellerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ProductID, ProductName, Price, SellerName);
    }
}




