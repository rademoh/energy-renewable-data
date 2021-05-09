package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rabiu Ademoh
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

      UserEntity findByUsername(String username);
      UserEntity getById(Long id);
      Optional<UserEntity> findByUserId(String userId);

}
