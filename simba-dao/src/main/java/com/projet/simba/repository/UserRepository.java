package com.projet.simba.repository;

import com.projet.simba.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByMotDePasseAndDeleteAtIsNull(String password);

    Optional<Users> findByAdresseMailAndDeleteAtNull(String adresseMail);

    Optional<Users> findByIdAndDeleteAtIsNull(UUID id);
}