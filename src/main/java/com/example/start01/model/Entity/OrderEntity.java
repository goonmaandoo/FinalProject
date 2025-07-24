package com.example.start01.model.Entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn (name="room_id")
    private RoomEntity roomid;

    @ManyToOne
    @JoinColumn (name="user_id")
    private UserEntity userid;

    @ManyToOne
    @JoinColumn (name="store_id")
    private StoreEntity storeid;

    @Column (columnDefinition = "jsonb", name = "room_order")
    private JsonNode roomorder;

    @Column (name="total_price")
    private Integer totalprice;

    @Column (name="created_at")
    private LocalDateTime createdat;

}
