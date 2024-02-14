package org.example;

import io.javalin.Javalin;
import org.example.Controller.Controller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //instantiation of logger for logging
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {


//        instantiate & inject all dependencies of my project
        SellerService sellerService = new SellerService();
        ProductService productService =new ProductService(sellerService);
        Controller controller =new Controller(productService,sellerService);


            Javalin api= controller.getAPI();
            api.start(9004);

    }

}