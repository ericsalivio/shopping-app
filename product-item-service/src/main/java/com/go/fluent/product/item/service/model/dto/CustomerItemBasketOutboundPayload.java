package com.go.fluent.product.item.service.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerItemBasketOutboundPayload extends BaseDTO{


    private String customerName;

    private List<ItemOutboundPayload> items = new ArrayList<>();


}
