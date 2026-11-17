package com.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String product;
    private Integer quantity;
    private String createdBy;

}
