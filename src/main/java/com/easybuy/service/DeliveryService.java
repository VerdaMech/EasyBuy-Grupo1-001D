package com.easybuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easybuy.model.Delivery;
import com.easybuy.repository.DeliveryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public List<Delivery> findAll(){
        return deliveryRepository.findAll();
    }

    public Delivery findById(Long id){
        return deliveryRepository.findById(id).get();
    }

    public Delivery save(Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    public void delete(Long id){
        deliveryRepository.deleteById(id);
    }

    public Delivery patchDelivery(Long id, Delivery parcialDelivery){
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
        if (deliveryOptional.isPresent()) {
            
            Delivery deliveryToUpdate = deliveryOptional.get();
            
            if (parcialDelivery.getName_delivery() != null) {
                deliveryToUpdate.setName_delivery(parcialDelivery.getName_delivery());
            }
            return deliveryRepository.save(deliveryToUpdate);
        } else {
            return null;
        }
    }
}
