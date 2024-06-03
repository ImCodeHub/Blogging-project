package com.Blogging.Platform.Blog.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("list")
    // @PreAuthorize("hasAuthority('admin:read')")
    public String getAdmin() {
        return "Secured Endpoint: : GET - Admin controller.";
    }
    
    @PostMapping("post")
    // @PreAuthorize("hasAuthority('admin:create')")
    public String post(){
        return "Secured Endpoint: : POST - Admin controller.";
    }

}
