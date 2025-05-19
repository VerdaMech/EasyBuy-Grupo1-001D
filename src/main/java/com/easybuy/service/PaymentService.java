package com.easybuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.model.Payment;
import com.easybuy.repository.PaymentRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public Payment findById(Long id){
        return paymentRepository.findById(id).get();
    }

    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }

    public void delete(Long id){
        paymentRepository.deleteById(id);
    }

    public Payment patchPayment(Long id, Payment parcialPayment){
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            
            Payment paymentToUpdate = paymentOptional.get();
            
            if (parcialPayment.getDescription() != null) {
                paymentToUpdate.setDescription(parcialPayment.getDescription());
            }
            return paymentRepository.save(paymentToUpdate);
        } else {
            return null;
        }
    }
}
