package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CustomerEntity;
import edu.wat.tim.lab1.model.PositionInCartEntity;
import edu.wat.tim.lab1.model.ProductsEntity;
import edu.wat.tim.lab1.repository.CartEntityRepository;
import edu.wat.tim.lab1.repository.CustomerEntityRepository;
import edu.wat.tim.lab1.repository.PositionInCartRepository;
import edu.wat.tim.lab1.repository.ProductsEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final CustomerEntityRepository customerEntityRepository;
    private final CartEntityRepository cartEntityRepository;
    private final ProductsEntityRepository productsEntityRepository;
    private final PositionInCartRepository positionInCartRepository;

    public CustomerEntity createCustomerEntity(CustomerEntity entity) {
        return customerEntityRepository.save(entity);
        //return customerEntityRepository.save(new CustomerEntity(entity.getCustomerName()));
    }

    public List<CustomerEntity> getAllEntities() {
        return customerEntityRepository.findAll();
    }

    public CartEntity addCartEntity(CartEntity cartEntity, Long customerId) {
        CustomerEntity customerEntity = customerEntityRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + customerId));
        cartEntity.setCustomerEntity(customerEntity);
        return cartEntityRepository.save(cartEntity);
    }

    //public ProductsEntity addProductEntity

    //wyszukiwanie produktu po nazwie
    public List<ProductsEntity> getProductName(String productName){
        return productsEntityRepository.getByProductName(productName);
    }

    //dodawanie produktu do koszyka
    public ProductsEntity addProductEntity(ProductsEntity productsEntity, Long cartId, Integer quantity){
        PositionInCartEntity positionInCartEntity = new PositionInCartEntity();
        CartEntity cartEntity = cartEntityRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + cartId));
        productsEntity = productsEntityRepository.save(productsEntity);
        positionInCartEntity.setCartEntity(cartEntity);
        positionInCartEntity.setProductEntity(productsEntity);
        positionInCartEntity.setProductQuantity(quantity);
        positionInCartEntity = positionInCartRepository.save(positionInCartEntity);
        return productsEntity;

    }

//    public void deleteProductEntity(Long productId){
//        productsEntityRepository.deleteById(productId);
//    }


    public void deleteCartEntity(Long cartId) {
        cartEntityRepository.deleteById(cartId);
    }

    public CustomerEntity updateEntity(CustomerEntity updatedEntity, Long id) {
        CustomerEntity entity = customerEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + id));

        entity.setCustomerName(updatedEntity.getCustomerName());
        return customerEntityRepository.save(entity);
    }

    public void deleteProduct(Long productId) {
        positionInCartRepository.deleteByProductEntityId(productId);
        productsEntityRepository.deleteById(productId);
    }

    public PositionInCartEntity updateCart(Integer newQuantity, Long cartId, Long productId){
        CartEntity cartEntity = cartEntityRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + cartId));

        ProductsEntity productsEntity = productsEntityRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + productId));

        PositionInCartEntity positionInCartEntity = positionInCartRepository.getByCartEntityIdAndProductEntityId(cartId, productId);
        if (newQuantity < 1){
            throw new RuntimeException("Wartość nie może być ujemna");
        }

        positionInCartEntity.setProductQuantity(newQuantity);
        return positionInCartRepository.save(positionInCartEntity);

    }
}
