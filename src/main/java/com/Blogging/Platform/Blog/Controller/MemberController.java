package com.Blogging.Platform.Blog.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blogging.Platform.Blog.Entity.User;
import com.Blogging.Platform.Blog.Model.BlogPost;
import com.Blogging.Platform.Blog.Model.UserBlogPost;
import com.Blogging.Platform.Blog.Service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/management")
public class MemberController {
    @Autowired
    private PostService postService;

    @PostMapping("post")
    @Operation(summary = "Create a new blog post", description = "Creates a new blog post for the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Blog post created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<String> createPost(@RequestBody BlogPost blogPost, @AuthenticationPrincipal User user) {
        String response = postService.createPost(blogPost, user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/my_post")
    @Operation(summary = "Get posts by authenticated user", description = "Retrieves all blog posts created by the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of blog posts retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<List<UserBlogPost>> getPostByAuthenticatedUser(@AuthenticationPrincipal User user) {

        List<UserBlogPost> posts = postService.getPostByAuthor(user.getId(), user.getFirstName());

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("post/{id}")
    @Operation(summary = "Get post by ID", description = "Retrieves a specific blog post by its ID. Accessible only to the author or authorized users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog post retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public ResponseEntity<List<UserBlogPost>> getPostById(@PathVariable Integer id,
            @AuthenticationPrincipal User user) {
        List<UserBlogPost> response = postService.findPostById(id, user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update_post/{id}")
    @Operation(summary = "Update a blog post", description = "Updates an existing blog post. Accessible only to the author or authorized users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog post updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public ResponseEntity<String> updatePost(@PathVariable Integer id, @RequestBody BlogPost blogPost,
            @AuthenticationPrincipal User user) {
        String response = postService.updatePost(id, blogPost, user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete_post/{id}")
    @Operation(summary = "Delete a blog post", description = "Deletes an existing blog post. Accessible only to the author or authorized users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog post deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public ResponseEntity<Boolean> DeleteBlogPost(@PathVariable Integer id,
            @AuthenticationPrincipal User user) {
        Boolean response = postService.deleteBlogPost(id, user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
