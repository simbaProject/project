package com.projet.simba.repository;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.simba.model.UserCanMark;

@Repository
public interface UserCanMarkRepository extends JpaRepository<UserCanMark,UUID> {
    Optional<UserCanMark> findByIdAndDeleteAtIsNull(UUID userId);
}
