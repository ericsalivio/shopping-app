package com.go.fluent.customer.service.service;

import com.go.fluent.customer.service.model.dto.CustomerDTO;
import com.go.fluent.customer.service.model.dto.CustomerItemBasketOutboundPayload;

public interface ICustomerService {

    void saveCustomerItemBasket(CustomerDTO requestPayload);

    CustomerItemBasketOutboundPayload getAllCustomerItems(String customerName);

}
