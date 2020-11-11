package com.go.fluent.product.item.service.mapperfacade.impl;


import com.go.fluent.product.item.service.mapperfacade.AbstractMapperFacadeFactory;
import com.go.fluent.product.item.service.model.dto.CustomerDTO;
import com.go.fluent.product.item.service.model.dto.CustomerItemBasketOutboundPayload;
import com.go.fluent.product.item.service.mapper.CustomerItemsMapper;
import com.go.fluent.product.item.service.mapper.CustomerItemsOutboundMapper;
import com.go.fluent.product.item.service.model.entity.CustomerBasketEntity;
import lombok.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;


@Primary
@Component("customMapperFacadeFactory")
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class CustomMapperFacadeFactory implements AbstractMapperFacadeFactory {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Autowired
    private CustomerItemsMapper<CustomerDTO, CustomerBasketEntity> customerItemsMapper;

    @Autowired
    private CustomerItemsOutboundMapper<CustomerItemBasketOutboundPayload, CustomerBasketEntity> customerItemsOutboundMapper;

    @PostConstruct
    public void init() {
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
    }

    @Override
    public MapperFacade getObject() throws Exception {
        addCustomerItemsMapper(mapperFactory);
        addCustomerItemsOutboundMapper(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

     void addCustomerItemsMapper(MapperFactory mapperFactory) {
        mapperFactory
                .classMap(CustomerDTO.class, CustomerBasketEntity.class)
                .customize(customerItemsMapper).byDefault().mapNulls(false)
                .mapNullsInReverse(false).register();
    }

    void addCustomerItemsOutboundMapper(MapperFactory mapperFactory) {
        mapperFactory
                .classMap(CustomerItemBasketOutboundPayload.class, CustomerBasketEntity.class)
                .customize(customerItemsOutboundMapper).byDefault().mapNulls(false)
                .mapNullsInReverse(false).register();
    }



}