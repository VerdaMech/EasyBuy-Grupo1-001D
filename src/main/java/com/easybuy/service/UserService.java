package com.easybuy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easybuy.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import com.easybuy.model.User;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User patchUser(Long id, User partialUser){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){

            User userToUpdate = userOptional.get();


            if(partialUser.getName() != null){
                userToUpdate.setName(partialUser.getName());
            }

            if(partialUser.getPassword() != null){
                userToUpdate.setPassword(partialUser.getPassword());
            }

            if(partialUser.getLast_Name() != null){
                userToUpdate.setLast_Name(partialUser.getLast_Name());
            }

            if(partialUser.getEmail() != null){
                userToUpdate.setEmail(partialUser.getEmail());
            }

            if(partialUser.getAddress() != null){
                userToUpdate.setAddress(partialUser.getAddress());
            }

            if(partialUser.getPhone_Number() != null){
                userToUpdate.setPhone_Number(partialUser.getPhone_Number());
            }
            return userRepository.save(userToUpdate);
        }else{
            return null;
        }
    }
}
