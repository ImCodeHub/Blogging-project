package com.Blogging.Platform.Blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blogging.Platform.Blog.Entity.User;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User>findByEmail(String email);
}
