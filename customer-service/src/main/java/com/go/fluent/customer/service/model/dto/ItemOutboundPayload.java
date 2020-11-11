package com.go.fluent.customer.service.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemOutboundPayload extends BaseDTO{

    private String itemId;
    private Integer quantity;
}
