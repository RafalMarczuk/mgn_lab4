package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "e-mail")
    private String email;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<CartEntity> childEntities = new ArrayList<>();

    public CustomerEntity(String customerName) {
        this.customerName = customerName;
    }



}
