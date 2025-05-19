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

import com.easybuy.model.Delivery;
import com.easybuy.service.DeliveryService;

@RestController
@RequestMapping("/api/v1/Deliverys")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<Delivery>> listar(){
        List<Delivery> listaDeliverys = deliveryService.findAll();
        if(listaDeliverys.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaDeliverys);
    }

    @PostMapping
    public ResponseEntity<Delivery> guardar(@RequestBody Delivery delivery){
        Delivery newDelivery = deliveryService.save(delivery);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDelivery);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> buscar(@PathVariable Long id){
        try{
            Delivery delivery = deliveryService.findById(id);
            return ResponseEntity.ok(delivery);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> actualizar(@PathVariable Long id, @RequestBody Delivery delivery){
        try{
            deliveryService.save(delivery);
            return ResponseEntity.ok(delivery);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Delivery> patchDelivery(@PathVariable Long id, @RequestBody Delivery partialDelivery) {
        try {
            Delivery updateDelivery = deliveryService.patchDelivery(id, partialDelivery);
            return ResponseEntity.ok(updateDelivery);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            deliveryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
