package com.easybuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.easybuy.model.OrderState;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {

}
