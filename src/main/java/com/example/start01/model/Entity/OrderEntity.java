package com.example.start01.model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table (name = "order")
public class OrderEntity {
    @Id
    @Column(name="order_id")
    private Integer orderid;

//    @JoinColumn (name="room_id")
//    private RoomEntity roomid;
//
//    @JoinColumn (name="user_id")
//    private UserEntity userid;
//
//    @JoinColumn (name="store_id")
//    private StoreEntity storeid;


}
