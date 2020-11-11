package com.go.fluent.customer.service.mapper;


import com.go.fluent.customer.service.model.dto.CustomerDTO;
import com.go.fluent.customer.service.model.dto.CustomerItemDTO;
import com.go.fluent.customer.service.model.entity.CustomerBasketEntity;
import com.go.fluent.customer.service.model.entity.CustomerItemEntity;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class CustomerItemsMapper<A extends CustomerDTO, B extends CustomerBasketEntity> extends CustomMapper<A, B> {

    @Override
    public void mapAtoB(A customerInbound, B customerEntity, MappingContext context) {

        super.mapAtoB(customerInbound, customerEntity, context);
        List<CustomerItemEntity> items;
        CustomerItemEntity customerItemEntity;
        if(!CollectionUtils.isEmpty(customerInbound.getItems())){
            items = new ArrayList<>();
            for (CustomerItemDTO item : customerInbound.getItems()) {
                    customerItemEntity = new CustomerItemEntity();
                    customerItemEntity.setItemId(item.getItemId());
                    customerItemEntity.setQuantity(item.getQuantity());
                    customerItemEntity.setStatus(1);
                    customerItemEntity.setCustomerBasket(customerEntity);
                    items.add(customerItemEntity);
            }
            customerEntity.setItems(items);
        }

    }


}