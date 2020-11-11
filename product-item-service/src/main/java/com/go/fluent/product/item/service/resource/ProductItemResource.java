package com.go.fluent.product.item.service.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.go.fluent.product.item.service.model.entity.ItemEntity;
import com.go.fluent.product.item.service.model.entity.ProductEntity;
import com.go.fluent.product.item.service.repository.ItemRepository;
import com.go.fluent.product.item.service.repository.ProductRepository;
import io.swagger.annotations.ApiOperation;

import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:4200")
@RequestMapping("/product-item")
public class ProductItemResource {
    Logger logger= LoggerFactory.getLogger(ProductItemResource.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MapperFacade mapperFacade;


    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @ApiOperation(httpMethod = "GET", value = "Get all products", notes = "Get products")
    @ResponseBody
    @GetMapping(value = "/getAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProducts() throws JsonProcessingException {
        List<ProductEntity> response = productRepository.findAll();
        logger.info("Product response : {}",mapper.writeValueAsString(response));
        return new ResponseEntity<>(mapper.writeValueAsString(response),HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "GET", value = "Get by item id", notes = "Get Item Products")
    @ResponseBody
    @GetMapping(value = "/findByItemId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByItemId(@QueryParam("itemId") String itemId) throws JsonProcessingException {
        ItemEntity response = itemRepository.findByItemId(itemId);
        return new ResponseEntity<>(mapper.writeValueAsString(response),HttpStatus.OK);
    }

}
