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

import com.easybuy.model.Payment;
import com.easybuy.service.PaymentService;

@RestController
@RequestMapping("/api/v1/Payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> listar(){
        List<Payment> listaPayments = paymentService.findAll();
        if(listaPayments.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaPayments);
    }

    @PostMapping
    public ResponseEntity<Payment> guardar(@RequestBody Payment payment){
        Payment newPayment = paymentService.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> buscar(@PathVariable Long id){
        try{
            Payment payment = paymentService.findById(id);
            return ResponseEntity.ok(payment);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> actualizar(@PathVariable Long id, @RequestBody Payment payment){
        try{
            paymentService.save(payment);
            return ResponseEntity.ok(payment);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Payment> patchPayment(@PathVariable Long id, @RequestBody Payment partialPayment) {
        try {
            Payment updatePayment = paymentService.patchPayment(id, partialPayment);
            return ResponseEntity.ok(updatePayment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            paymentService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
