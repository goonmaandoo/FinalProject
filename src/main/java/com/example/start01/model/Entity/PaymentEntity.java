package com.example.start01.model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table (name = "payment")
public class PaymentEntity {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userid;

    @Column(columnDefinition = "json")
    private String status;

    private Integer amount;
}
