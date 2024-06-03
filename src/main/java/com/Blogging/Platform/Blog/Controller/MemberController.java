package com.Blogging.Platform.Blog.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blogging.Platform.Blog.Entity.Post;
import com.Blogging.Platform.Blog.Entity.User;
import com.Blogging.Platform.Blog.Model.BlogPost;
import com.Blogging.Platform.Blog.Model.UserBlogPost;
import com.Blogging.Platform.Blog.Service.PostService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/v1/management")
public class MemberController {
    @Autowired
    private PostService postService;

    @PostMapping("post")
    public ResponseEntity<String> createPost(@RequestBody BlogPost blogPost, @AuthenticationPrincipal User user ){
        String response = postService.createPost(blogPost, user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/my_post")
    public ResponseEntity<List<UserBlogPost>> getPostByAuthenticatedUser(@AuthenticationPrincipal User user) {

        List<UserBlogPost> posts = postService.getPostByAuthor(user.getId(), user.getFirstName());

        return new ResponseEntity<>(posts, HttpStatus.OK); 
    }

    @GetMapping("post/{id}")
    public ResponseEntity<List<UserBlogPost>> getPostById(@PathVariable Integer id, @AuthenticationPrincipal User user){    
        List<UserBlogPost> response = postService.findPostById(id, user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
