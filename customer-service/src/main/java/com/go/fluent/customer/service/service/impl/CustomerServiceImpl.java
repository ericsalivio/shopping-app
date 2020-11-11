package com.go.fluent.customer.service.service.impl;

import com.go.fluent.customer.service.model.dto.CustomerDTO;
import com.go.fluent.customer.service.model.dto.CustomerItemBasketOutboundPayload;
import com.go.fluent.customer.service.model.dto.CustomerItemDTO;
import com.go.fluent.customer.service.model.entity.CustomerBasketEntity;
import com.go.fluent.customer.service.model.entity.CustomerItemEntity;
import com.go.fluent.customer.service.repository.CustomerRepository;
import com.go.fluent.customer.service.service.ICustomerService;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    Logger logger= LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void saveCustomerItemBasket(CustomerDTO requestPayload) {
        CustomerBasketEntity customerRecord = customerRepository.findCustomerName(requestPayload.getCustomerName());
        logger.info("Customer request payload :{}", requestPayload);
        CustomerBasketEntity customerItems;
        if (Objects.isNull(customerRecord)) {
            customerItems = mapperFacade.map(requestPayload, CustomerBasketEntity.class);
            customerRepository.save(customerItems);
        } else {
            List<CustomerItemDTO> existItems = requestPayload.getItems().stream().filter(ele ->
                    customerRecord.getItems().stream()
                            .anyMatch(element -> element.getItemId().equals(ele.getItemId()))).collect(Collectors.toList());

            requestPayload.getItems().removeAll(existItems);


            customerRecord.getItems().stream().forEach(customerItemEntity -> {
                Optional<CustomerItemDTO> customerItemDTO = existItems.stream().filter(inboundItems ->
                        inboundItems.getItemId().equals(customerItemEntity.getItemId())
                ).findFirst();

                if (customerItemDTO.isPresent() && customerItemDTO.get().getQuantity() > 0) {
                    customerItemEntity.setQuantity(customerItemDTO.get().getQuantity());
                    customerItemEntity.setStatus(1);
                } else {
                    customerItemEntity.setStatus(0);
                }
            });

            requestPayload.getItems().forEach(newItems -> {
                CustomerItemEntity customerItemDTO = new CustomerItemEntity()
                        .builder().itemId(newItems.getItemId())
                        .status(1)
                        .quantity(newItems.getQuantity()).customerBasket(customerRecord).build();

                customerRecord.getItems().add(customerItemDTO);
            });

            customerRepository.save(customerRecord);
        }


    }

    @Override
    public CustomerItemBasketOutboundPayload getAllCustomerItems(String customerName) {
        logger.info("get customer items by name: {}",customerName);
        CustomerBasketEntity customerItems = customerRepository.findCustomerName(customerName);
        CustomerItemBasketOutboundPayload response = mapperFacade.map(customerItems, CustomerItemBasketOutboundPayload.class);
        logger.info("customer items: {}",response);
        return response;
    }

}
