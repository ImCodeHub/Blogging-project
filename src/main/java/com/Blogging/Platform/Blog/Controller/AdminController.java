package com.Blogging.Platform.Blog.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blogging.Platform.Blog.Model.UserBlogPost;
import com.Blogging.Platform.Blog.Model.UserListModel;
import com.Blogging.Platform.Blog.Service.AdminService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("/all_post")
    // @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<UserBlogPost>> getAllPosts() {
        logger.info("Admin user requesting list of all Posts");
        List<UserBlogPost> list = adminService.getAllPost();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("userList")
    public ResponseEntity<List<UserListModel>> getAllUser() {
        logger.info("Admin user requesting list of all users");
        List<UserListModel> list = adminService.getAllAuthor();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> deletePostById(@PathVariable Integer id){
        Boolean response = adminService.deletePost(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<Boolean> deleteAuthorById(@PathVariable Integer id){
        Boolean response = adminService.deleteAuthor(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
   