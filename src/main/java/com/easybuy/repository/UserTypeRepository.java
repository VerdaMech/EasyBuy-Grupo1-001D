package com.easybuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easybuy.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long>{

}
