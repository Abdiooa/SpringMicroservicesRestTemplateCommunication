package com.microspring.usersservice.repositories;

import com.microspring.usersservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    boolean existsByEmail(String Email);
}
