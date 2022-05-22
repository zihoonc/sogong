package com.kanari.waiting.repository;

import com.kanari.waiting.domain.WaitingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingRepository extends JpaRepository<WaitingEntity, Long> {

}
