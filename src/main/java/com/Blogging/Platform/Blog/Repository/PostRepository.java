package com.Blogging.Platform.Blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blogging.Platform.Blog.Entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByAuthorId(Integer authorId);
    List<Post> findByPostIdAndAuthorId(Integer postId, Integer authorId);
}
