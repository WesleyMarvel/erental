package com.example.erental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
public class RentalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String itemType;
    @Column
    private Integer rentalPeriod;
    @Column
    private Integer quantity;
    @Column
    private String location;
    @Column
    private Float price;
    @Column
    private String itemImage;
    @ManyToOne
    private Users users;
}
