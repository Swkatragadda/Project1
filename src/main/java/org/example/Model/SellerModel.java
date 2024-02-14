package org.example.Model;

import java.util.Objects;

public class SellerModel {

    private String SellerName;

    public SellerModel(){

    }
        public SellerModel(String sellerName) {
        SellerName = sellerName;

    }
    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerModel that = (SellerModel) o;
        return Objects.equals(SellerName, that.SellerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SellerName);
    }

    @Override
    public String toString() {
        return "SellerModel{" +
                "SellerName='" + SellerName + '\'' +
                '}';
    }
}
