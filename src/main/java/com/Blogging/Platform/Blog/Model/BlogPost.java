package com.Blogging.Platform.Blog.Model;

import java.time.LocalDateTime;

import com.Blogging.Platform.Blog.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BlogPost {
    
    private String title;

    private String content;

    private User author;

    private LocalDateTime createdAt;



}
