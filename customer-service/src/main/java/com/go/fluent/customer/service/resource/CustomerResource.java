package com.go.fluent.customer.service.resource;

import com.go.fluent.customer.service.model.dto.CustomerDTO;
import com.go.fluent.customer.service.model.dto.CustomerItemBasketOutboundPayload;
import com.go.fluent.customer.service.service.ICustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin("http://localhost:4200")
@RequestMapping("/customer")
public class CustomerResource {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private ICustomerService customerService;


    @ApiOperation(httpMethod = "POST", value = "Save customer item basket", notes = "Save customer item basket")
    @PostMapping(value = "/saveCustomerItemBasket")
    public ResponseEntity<Void> saveCustomerItemBasket(@ApiParam(value = "Save customer item basket", required = true)
                                                            @RequestBody CustomerDTO customerItemPayload) {
            customerService.saveCustomerItemBasket(customerItemPayload);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(httpMethod = "GET",
            value = "Get customer item basket", notes = "Get customer item basket",
            response = CustomerItemBasketOutboundPayload.class)
    @ResponseBody
    @GetMapping(value = "/customerItemBasket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerItemBasketOutboundPayload> getAllCustomerItems(@ApiParam(value = "Get customer item basket", required = true)
                                                                                     @RequestParam ("name") String name) {
        CustomerItemBasketOutboundPayload response = customerService.getAllCustomerItems(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
