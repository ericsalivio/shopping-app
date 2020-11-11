package com.go.fluent.customer.service.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "CUSTOMER")
public class CustomerBasketEntity  {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "CUSTOMER_BASKET_ID")
    private String customerBasketId;

    @Valid
    @NotNull(message = "\"The Item code field cannot be null.\"")
    @Column(name = "FIRST_NAME")
    private String customerName;

    @OneToMany( mappedBy = "customerBasket",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerItemEntity> items = new ArrayList<>();

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal totalPrice;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
