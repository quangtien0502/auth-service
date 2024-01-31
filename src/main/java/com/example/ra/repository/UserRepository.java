package com.example.ra.repository;

import com.example.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    List<User> findAllByFullNameContaining(String keyword);

    //Todo: Ask Hai how to add and delete role for user?
}
