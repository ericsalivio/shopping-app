package com.go.fluent.product.item.service.mapper;


import com.go.fluent.product.item.service.model.dto.CustomerItemBasketOutboundPayload;
import com.go.fluent.product.item.service.model.dto.ItemOutboundPayload;
import com.go.fluent.product.item.service.model.entity.CustomerBasketEntity;
import com.go.fluent.product.item.service.model.entity.CustomerItemEntity;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class CustomerItemsOutboundMapper<A extends CustomerItemBasketOutboundPayload, B extends CustomerBasketEntity> extends CustomMapper<A, B> {

    @Override
    public void mapAtoB(A customerOutbound, B customerEntity, MappingContext context) {
        super.mapAtoB(customerOutbound, customerEntity, context);
        List<ItemOutboundPayload> items;
        ItemOutboundPayload itemOutboundPayload;
        if(!CollectionUtils.isEmpty(customerEntity.getItems())){
            items = new ArrayList<>();
            for (CustomerItemEntity item : customerEntity.getItems()) {
                itemOutboundPayload = new ItemOutboundPayload();
                itemOutboundPayload.setItemId(item.getItemId());
                itemOutboundPayload.setQuantity(item.getQuantity());
                items.add(itemOutboundPayload);
            }
            customerOutbound.setItems(items);
        }

    }


}