package edu.wat.tim.lab1.repository;

import edu.wat.tim.lab1.model.PositionInCartEntity;
import edu.wat.tim.lab1.model.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PositionInCartRepository extends JpaRepository<PositionInCartEntity, Long> {

    public PositionInCartEntity getByCartEntityIdAndProductEntityId(Long cartId, Long productId );
    @Transactional
    public void deleteByProductEntityId(Long productId);

}
