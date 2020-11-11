package com.go.fluent.product.item.service.repository;

import com.go.fluent.product.item.service.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@CrossOrigin("http://localhost:4200")
//@RepositoryRestResource(collectionResourceRel = "product",path = "product")
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {


    ProductEntity findByName(String name);

//    @RestResource(path = "findProductByItemId")
    ProductEntity findByItemsItemId(@Param("itemId") String itemId);
}
