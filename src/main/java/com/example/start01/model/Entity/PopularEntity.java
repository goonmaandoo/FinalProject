package com.example.start01.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table (name="popular")
public class PopularEntity {
    @Id
    private Integer id;

    private String title;

    private Integer price;

    @Column(name = "store_id")
    private Integer store_id;

}
