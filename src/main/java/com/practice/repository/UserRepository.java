package com.practice.repository;

import com.practice.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByPhone(Long phone);
}
