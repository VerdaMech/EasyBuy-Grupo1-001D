package com.easybuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.model.OrderState;
import com.easybuy.repository.OrderStateRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderStateService {

    @Autowired
    private OrderStateRepository orderStateRepository;

    public List<OrderState> findAll(){
        return orderStateRepository.findAll();
    }

    public OrderState findById(Long id){
        return orderStateRepository.findById(id).get();
    }

    public OrderState save(OrderState orderState){
        return orderStateRepository.save(orderState);
    }

    public void delete(Long id){
        orderStateRepository.deleteById(id);
    }

    public OrderState patchOrderState(Long id, OrderState parcialOrderState){
        Optional<OrderState> orderStateOptional = orderStateRepository.findById(id);
        if (orderStateOptional.isPresent()) {
            
            OrderState orderStateToUpdate = orderStateOptional.get();
            
            if (parcialOrderState.getDescription() != null) {
                orderStateToUpdate.setDescription(parcialOrderState.getDescription());
            }
            return orderStateRepository.save(orderStateToUpdate);
        } else {
            return null;
        }
    }
}
