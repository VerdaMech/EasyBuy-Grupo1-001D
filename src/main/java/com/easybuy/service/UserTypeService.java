package com.easybuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.model.UserType;
import com.easybuy.repository.UserTypeRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserTypeService {
    
    @Autowired
    private UserTypeRepository userTypeRepository;

    public List<UserType> findAll(){
        return userTypeRepository.findAll();
    }

    public UserType findById(Long id){
        return userTypeRepository.findById(id).get();
    }

    public UserType save(UserType userType){
        return userTypeRepository.save(userType);
    }

    public void delete(Long id){
        userTypeRepository.deleteById(id);
    }

    public UserType patchUserType(Long id, UserType parcialUserType){
        Optional<UserType> userTypeOptional = userTypeRepository.findById(id);
        if (userTypeOptional.isPresent()) {
            
            UserType userTypeToUpdate = userTypeOptional.get();
            
            if (parcialUserType.getDescription() != null) {
                userTypeToUpdate.setDescription(parcialUserType.getDescription());
            }
            return userTypeRepository.save(userTypeToUpdate);
        } else {
            return null;
        }
    }
}
