package com.go.fluent.product.item.service.repository;

import com.go.fluent.product.item.service.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//@CrossOrigin("http://localhost:4200")
//@RepositoryRestResource(collectionResourceRel = "product-items",path = "product-items")
@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

//    @RestResource(path = "findByItemId")
    ItemEntity findByItemId(@Param("itemId") String itemId);

}
