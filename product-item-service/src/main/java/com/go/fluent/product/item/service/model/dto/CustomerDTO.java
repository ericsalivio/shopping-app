package com.go.fluent.product.item.service.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends BaseDTO{

    @NotNull(message = "Name cannot be null")
    private String customerName;

    private List<CustomerItemDTO> items = new ArrayList<>();

}
