package com.app.backend.api.repository.users;


import com.app.backend.api.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByExternalId(String externalId);

    boolean existsByExternalId(String externalId);
}