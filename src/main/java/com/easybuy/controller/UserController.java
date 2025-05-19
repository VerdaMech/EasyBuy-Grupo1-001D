package com.easybuy.controller;

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
import com.easybuy.service.UserService;
import java.util.List;
import com.easybuy.model.User;

@RestController
@RequestMapping("/api/v1/Users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listar(){
        List <User> users = userService.findAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/id")
    public ResponseEntity<User> buscar(@PathVariable Long id){
        try{
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        }catch  (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User newcUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newcUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer run, @RequestBody User user){
        try{
            userService.save(user);
            return ResponseEntity.ok(user);
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable Long id, @RequestBody User partialUser){
        try{
            User updatedUser = userService.patchUser(id, partialUser);
            return ResponseEntity.ok(updatedUser);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        try{
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }catch( Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
