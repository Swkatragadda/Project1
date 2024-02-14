package Tests;

import org.example.Exception.ProductException;
import org.example.Model.ProductModel;
import org.example.Model.SellerModel;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SellerProductTests {

    private ProductService productService;
    private SellerService sellerService;

@Before

public void setUp(){
        sellerService= new SellerService();
        productService=new ProductService(sellerService);
}
@Test

    public void testaddAllProducts()throws ProductException {
        SellerModel seller = new SellerModel("homedepot");
        sellerService.addSeller(seller);
        long ProductID = (long) (Math.random() * Long.MAX_VALUE);
        ProductModel product = new ProductModel(ProductID,"Paint", 35.00, "homedepot");
        ProductModel addedProduct = productService.addProduct(product);
        Assert.assertEquals(product, addedProduct);
    }


@Test

   public void testAddProductWithNonExistingSeller(){
    Assert.assertThrows(ProductException.class,()->{
        long ProductID = (long) (Math.random() * Long.MAX_VALUE);
        ProductModel product =new ProductModel(ProductID,"Paint",40.00,"Lowes");
        productService.addProduct(product);
    });
}
@Test
    public void testUpdateProduct() throws ProductException {
    SellerModel seller = new SellerModel("Lowes");
    sellerService.addSeller(seller);
    long ProductID = (long) (Math.random() * Long.MAX_VALUE);
    ProductModel product = new ProductModel(ProductID,"Paint", 40.00, "Lowes");
    productService.addProduct(product);

    ProductModel updatedProduct = new ProductModel(ProductID,"Brush", 10.00, "Lowes");
    updatedProduct.setProductID(product.getProductID());

    boolean result = productService.updateProduct(updatedProduct);
    Assert.assertTrue(result);
}
@Test
    public void testDeleteProduct() throws ProductException{
    SellerModel seller =new SellerModel("Lowes");
    sellerService.addSeller(seller);
    long ProductID = (long) (Math.random() * Long.MAX_VALUE);
    ProductModel product =new ProductModel(ProductID,"Brush",10.00,"Lowes");
    productService.addProduct(product);
     boolean result =productService.deleteProduct(product.getProductID());
     Assert.assertTrue(result);

}
}


