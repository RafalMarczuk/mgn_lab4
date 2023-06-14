package edu.wat.tim.lab1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "measure")
    private String productMeasure;

//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable=false)
//    @JsonIgnore
//    private CartEntity cartEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<PositionInCartEntity> positionInCartEntities = new ArrayList<>();


}
