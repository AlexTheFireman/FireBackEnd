package com.group.appName.repositories;

import com.group.appName.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT u.username from UserEntity u")
    List<String> getAllUserNames();

    void deleteByUsername(String name);

    UserEntity getUserEntityByUsernameIsLike(String name);
}

