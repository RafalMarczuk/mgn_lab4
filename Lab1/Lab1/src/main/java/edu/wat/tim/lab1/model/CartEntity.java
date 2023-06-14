package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "cartName")
    private String cartName;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    @JsonIgnore
    private CustomerEntity customerEntity;

//    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL)
//    private List<ProductsEntity> productsEntities = new ArrayList<>();

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL)
    private List<PositionInCartEntity> positionInCartEntities = new ArrayList<>();

}
