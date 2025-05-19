package com.easybuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easybuy.model.OrderState;
import com.easybuy.service.OrderStateService;

@RestController
@RequestMapping("/api/v1/OrderStates")
public class OrderStateController {

    @Autowired
    private OrderStateService orderStateService;

    @GetMapping
    public ResponseEntity<List<OrderState>> listar(){
        List<OrderState> listaOrderStates = orderStateService.findAll();
        if(listaOrderStates.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaOrderStates);
    }

    @PostMapping
    public ResponseEntity<OrderState> guardar(@RequestBody OrderState orderState){
        OrderState newOrderState = orderStateService.save(orderState);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrderState);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderState> buscar(@PathVariable Long id){
        try{
            OrderState orderState = orderStateService.findById(id);
            return ResponseEntity.ok(orderState);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderState> actualizar(@PathVariable Long id, @RequestBody OrderState orderState){
        try{
            orderStateService.save(orderState);
            return ResponseEntity.ok(orderState);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderState> patchOrderState(@PathVariable Long id, @RequestBody OrderState partialOrderState) {
        try {
            OrderState updateOrderState = orderStateService.patchOrderState(id, partialOrderState);
            return ResponseEntity.ok(updateOrderState);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            orderStateService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
