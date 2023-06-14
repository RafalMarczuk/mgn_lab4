package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CustomerEntity;
import edu.wat.tim.lab1.model.PositionInCartEntity;
import edu.wat.tim.lab1.model.ProductsEntity;
import edu.wat.tim.lab1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleRestController {

    private final SampleService service;

    @GetMapping("/echo")
    public String echo(String value) {
        return value;
    }

    @GetMapping("/echo/{value}")
    public String echoPath(@PathVariable String value) {
        return value;
    }

    @PostMapping("/entity")
    public ResponseEntity<CustomerEntity> createCustomerEntity(@RequestBody CustomerEntity entity) {
        CustomerEntity savedEntity = service.createCustomerEntity(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping("/entity")
    public ResponseEntity<List<CustomerEntity>> getAllEntities() {
        List<CustomerEntity> entities = service.getAllEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping("/entity/{customerId}/cart")
    public ResponseEntity<CartEntity> addCartEntity(@RequestBody CartEntity entity, @PathVariable(value = "customerId") Long customerId) {
        CartEntity savedEntity = service.addCartEntity(entity, customerId);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    //wyszukiwanie produktu po nazwie
    @GetMapping("/products/{productName}")
    public ResponseEntity<List<ProductsEntity>> getProductByName(@PathVariable(value = "productName") String name){
        List<ProductsEntity> products = service.getProductName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/cart/{cartId}/product/{productQuantity}")
    public ResponseEntity<ProductsEntity> addProductEntity(@PathVariable(value = "cartId") Long cartId,
                                                           @PathVariable(value = "productQuantity") Integer quantity,
                                                           @RequestBody ProductsEntity productsEntity){
        ProductsEntity savedProduct = service.addProductEntity(productsEntity, cartId, quantity);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/entity/cart/{id}")
    public ResponseEntity<HttpStatus> deleteCartEntity(@PathVariable(value = "id") Long id) {
        service.deleteCartEntity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable(value = "productId") Long productId) {
        service.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/entity/{id}")
    public ResponseEntity<CustomerEntity> updateEntity(@RequestBody CustomerEntity entity, @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(service.updateEntity(entity, id), HttpStatus.OK);
    }

    @PutMapping("/cart/{cartId}/product/{productId}/{newQuantity}")
    public ResponseEntity<PositionInCartEntity> updateCart(@PathVariable(value = "cartId") Long cartId,
                                                           @PathVariable(value = "productId") Long productId,
                                                           @PathVariable(value = "newQuantity") Integer newQuantity){
        return new ResponseEntity<>(service.updateCart(newQuantity, cartId, productId), HttpStatus.OK);
    }
}
