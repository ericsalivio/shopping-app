package com.go.fluent.product.item.service.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemOutboundPayload extends BaseDTO{

    private String itemId;
    private Integer quantity;
}
