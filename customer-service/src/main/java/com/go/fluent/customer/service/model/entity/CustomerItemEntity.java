package com.go.fluent.customer.service.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "CUSTOMER_ITEM")
public class CustomerItemEntity  {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "CUSTOMER_ITEM_ID", nullable = false)
    private String customerItemId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
    @JoinColumn(name = "CUSTOMER_BASKET_ID")
    private CustomerBasketEntity customerBasket;

    @Column(name = "ITEM_ID")
    private String itemId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "STATUS")
    private int status;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
