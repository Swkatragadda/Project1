package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.ProductModel;
import org.example.Model.SellerModel;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class Controller {

     ProductService productService;
    SellerService sellerService;

    public Controller(ProductService productService, SellerService sellerService) {
        this.productService = productService;
        this.sellerService = sellerService;
    }


    public Javalin getAPI() {
        Javalin api = Javalin.create();
        /*  Check the health of the server */
        api.get("/health/", context -> {
            context.result("the server is UP");
        });
        //endpoint to retrieve all products.
        api.get("/product/", context -> {
            List<ProductModel> productList = productService.getProductList();
            context.json(productList);
        });
        // end point to retrieve all sellers
        api.get("seller/", context -> {
            Set<SellerModel> serviceSet = sellerService.getSellerSet();
            context.json(serviceSet);
        });
        //Endpoint to retrieve a specific product by ID.
        api.get("product/{ProductID}", context -> {
            long id = Long.parseLong(context.pathParam("ProductID"));
            ProductModel p = productService.getProductbyID(id);
            if (p == null) {
                context.status(404);
            } else {
                context.json(p);
                context.status(200);
            }
        });


        //Endpoint to add a new product and also chekcing whether the seller exists before creating a product
        api.post("product/", context -> {
                    try {
                        ObjectMapper om = new ObjectMapper();
                        ProductModel p = om.readValue(context.body(),ProductModel.class);
                        if(!sellerService.doesSellerExist(p.getSellerName())){
                            context.status(400);
                            context.result("Seller does not exist");
                            return;
                        }
                        ProductModel newProductModel= productService.addProduct(p);
                        Main.log.info(String.valueOf(newProductModel));
                        context.status(201);
                        context.json(newProductModel);
                    }catch (JsonProcessingException e){
                        context.status(400);
                    }catch(ProductException e){
                        context.result(e.getMessage());
                        context.status(400);
                    }
        });
        //Endpoint to add a new seller
        api.post("seller/",context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                SellerModel s = om.readValue(context.body(), SellerModel.class);
                sellerService.addSeller(s);
                context.status(201);
            }catch(JsonProcessingException e){
                context.status(400);
            }
        });
        //Endpoint to update a product by using Product ID
        api.put("product/{ProductID}",context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                ProductModel updatedProduct = om.readValue(context.body(), ProductModel.class);
                boolean updated = productService.updateProduct(updatedProduct);
                if (updated) {
                    context.status(200);
                    context.result("Product updated successfully");
                } else {
                    context.status(404);
                    context.result("Product not found");
                }
            } catch (JsonProcessingException e) {
                context.status(400);
                context.result("Error Processing Json data ");
            }
        });

        //Endpoint to delete a product
        api.delete("product/{ProductID}", ctx->{
            try {
                long id = Long.parseLong(ctx.pathParam("ProductID"));
                productService.deleteProduct((id));

                ctx.status(200);
                ctx.result("Product deleted successfully");
            } catch (NumberFormatException e){
                ctx.status(400);
                ctx.result( "Error parsing the product ID");

           }
        });

     return api;
    }
}
