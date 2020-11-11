package com.go.fluent.customer.service.repository;

import com.go.fluent.customer.service.model.entity.CustomerItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerItemRepository extends JpaRepository<CustomerItemEntity, Long> {

}
