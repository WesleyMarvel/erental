package com.example.erental.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicUpdate
public class Rented {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Users users;
    @ManyToOne
    private RentalItem rentalItem;
    @Column
    private Integer duration;
}
