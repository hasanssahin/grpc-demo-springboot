package com.hasansahin.discount.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String externalId;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Discount> discounts;
}
