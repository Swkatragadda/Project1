package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductService {

    SellerService sellerService;

    List<ProductModel> productList;

    public ProductService(SellerService sellerService) {
        this.sellerService = sellerService;
        this.productList = new ArrayList<>();

    }

    public List<ProductModel> getProductList() {
        Main.log.info("Getting List of Products:" + productList);
        return productList;
    }


    //Method to add product and check if seller exists before adding the product
   // checks if product name and seller name is not null and price is not less than 0
    public ProductModel addProduct(ProductModel p) throws ProductException {
        if (p.getProductName() == null || p.getSellerName() == null || p.getPrice()<0) {
            Main.log.info("throwing product exception if product name or seller name is null  and if price is less than 0:" + p);
            throw new ProductException("Invalid product details");
        }
        if (!sellerService.doesSellerExist(p.getSellerName())) {
            throw new ProductException("Seller does not exist");
        }
        long ProductID = (long) (Math.random() * Long.MAX_VALUE);
        p.setProductID(ProductID);
        productList.add(p);
        return p;
    }

    //Method to get product ID from Product list
    public ProductModel getProductbyID(Long ProductID) {
        Main.log.info("Retrieving product Id :" + productList);
        for (ProductModel currentProduct : productList) {
            if (currentProduct.getProductID() == ProductID) {
                return currentProduct;
            }
        }
        return null;

    }

    // Method to updateProduct that takes the updated attributes and iterates thru the product list
    public boolean updateProduct(ProductModel updatedProduct){
              long ProductID = updatedProduct.getProductID();
             for (ProductModel Product : productList) {
           if (Product.getProductID() == ProductID) {

               Product.setProductName(updatedProduct.getProductName());
               Product.setPrice(updatedProduct.getPrice());
               Product.setSellerName(updatedProduct.getSellerName());
               Main.log.info("updated Product ID:" + ProductID );
               return true;
           }
       }
           return false;
      }

       //Method to Delete the product by ProductID
       public boolean deleteProduct(long productId){
        Iterator<ProductModel> iterator=productList.iterator();
        while (iterator.hasNext()){
            ProductModel product =iterator.next();
            if(product.getProductID()==productId){
                iterator.remove();
                return true;
            }
        }
        return false ;
    }
    }




