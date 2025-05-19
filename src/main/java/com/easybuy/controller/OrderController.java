package com.easybuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybuy.model.Order;
import com.easybuy.service.OrderService;

@RestController
@RequestMapping("/api/v1/Orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listar(){
        List<Order> listaOrders = orderService.findAll();
        if(listaOrders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaOrders);
    }

    @PostMapping
    public ResponseEntity<Order> guardar(@RequestBody Order order){
        Order newOrder = orderService.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> buscar(@PathVariable Long id){
        try{
            Order order = orderService.findById(id);
            return ResponseEntity.ok(order);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> actualizar(@PathVariable Long id, @RequestBody Order order){
        try{
            orderService.save(order);
            return ResponseEntity.ok(order);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> patchOrder(@PathVariable Long id, @RequestBody Order partialOrder) {
        try {
            Order updateOrder = orderService.patchOrder(id, partialOrder);
            return ResponseEntity.ok(updateOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            orderService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
