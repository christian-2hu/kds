package com.scheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scheduler.models.ifood.OauthToken;

@Repository
public interface OauthTokenRepository extends JpaRepository<OauthToken, Long> {
    
}
