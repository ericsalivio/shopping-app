package com.go.fluent.customer.service.repository;

import com.go.fluent.customer.service.model.entity.CustomerBasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerBasketEntity, Long> {


    @Query("SELECT c FROM CustomerBasketEntity c " +
            "JOIN FETCH c.items ci WHERE c.customerName=:name AND ci.status=1")
    CustomerBasketEntity findCustomerName(@Param("name") String name);
}
