package com.easybuy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easybuy.model.Order;
import com.easybuy.repository.OrderRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        return orderRepository.findById(id).get();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }

    public Order patchOrder(Long id, Order parcialOrder){
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            
            Order orderToUpdate = orderOptional.get();
            
            if (parcialOrder.getDate() != null) {
                orderToUpdate.setDate(parcialOrder.getDate());
            }
            if (parcialOrder.getTotal() != null) {
                orderToUpdate.setTotal(parcialOrder.getTotal());
            }
            return orderRepository.save(orderToUpdate);
        } else {
            return null;
        }
    }
}
