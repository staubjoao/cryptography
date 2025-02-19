package com.api.cryptography.repositoris;

import com.api.cryptography.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
