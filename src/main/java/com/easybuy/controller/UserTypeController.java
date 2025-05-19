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
import com.easybuy.model.UserType;
import com.easybuy.service.UserTypeService;

@RestController
@RequestMapping("/api/v1/UserTypes")
public class UserTypeController {

    @Autowired
    private UserTypeService userTypeService;

    @GetMapping
    public ResponseEntity<List<UserType>> listar(){
        List <UserType> userTypes = userTypeService.findAll();
        if(userTypes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userTypes);
    }

    @GetMapping("/id")
    public ResponseEntity<UserType> buscar(@PathVariable Long id){
        try{
            UserType userType = userTypeService.findById(id);
            return ResponseEntity.ok(userType);
        }catch  (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserType> save(@RequestBody UserType userType){
        UserType newUserType = userTypeService.save(userType);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserType);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType> update(@PathVariable Long id, @RequestBody UserType userType){
        try{
            userTypeService.save(userType);
            return ResponseEntity.ok(userType);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserType> patchUserType(@PathVariable Long id, @RequestBody UserType partialUserType){
        try{
            UserType updatedUserType = userTypeService.patchUserType(id, partialUserType);
            return ResponseEntity.ok(updatedUserType);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        try{
            userTypeService.delete(id);
            return ResponseEntity.noContent().build();
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
