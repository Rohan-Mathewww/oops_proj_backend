package com.bitsbids.bitsbids.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bitsbids.bitsbids.entity.UserEntity;

// the repository is a specialised version of @Component,used to indicate that it fullfils the role of a repo
@Repository
public interface UserRepository extends JpaRepository<UserEntity,UUID>{
    // the second argument to the JpaRepository is the primary key of the entity
    
}