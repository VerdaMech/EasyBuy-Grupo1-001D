package com.easybuy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "Order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_order;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario", nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "user_run", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_method", nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "order_sate_id", nullable = false)
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "id_delivery", nullable = false)
    private Delivery delivery;
}
