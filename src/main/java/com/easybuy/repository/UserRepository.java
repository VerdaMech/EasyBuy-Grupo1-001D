package com.easybuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.easybuy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
