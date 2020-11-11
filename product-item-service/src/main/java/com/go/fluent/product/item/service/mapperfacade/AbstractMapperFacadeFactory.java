package com.go.fluent.product.item.service.mapperfacade;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.FactoryBean;


public interface AbstractMapperFacadeFactory extends FactoryBean<MapperFacade> {

    default  Class<MapperFacade> getObjectType(){
        return MapperFacade.class;
    }

    default boolean isSingleton(){
        return  true;
    }

}
