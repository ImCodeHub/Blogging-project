package com.Blogging.Platform.Blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Blogging.Platform.Blog.Entity.Role;
import com.Blogging.Platform.Blog.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT post.author AS author_id, CONCAT (user.first_name,' ',user.last_name) AS NAME , user.email, COUNT(post.author) AS blog_count FROM post INNER JOIN USER ON post.author = user.id GROUP BY user.id, user.first_name", nativeQuery = true)
    List<Object[]> findAllAuthorsWithPostCountNative();

    Optional<User> findByIdAndRole(Integer authorId, Role role );

    List<User> findByRole(Role role);
}
