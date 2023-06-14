package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsEntityRepository extends JpaRepository<ProductsEntity, Long> {

   public List<ProductsEntity> getByProductName(String name);


}
