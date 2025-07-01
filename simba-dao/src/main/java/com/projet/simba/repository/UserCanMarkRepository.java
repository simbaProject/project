package com.projet.simba.repository;

import com.projet.simba.model.UserCanMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCanMarkRepository extends JpaRepository<UserCanMark, UUID> {
  Optional<UserCanMark> findByIdAndDeleteAtIsNull(UUID userId);
}