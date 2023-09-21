package com.adroyoyo.ServidorApi.Repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adroyoyo.ServidorApi.Entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByUsername(String username);               // hacen lo mismo las dos

    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> getName(String username);
}
