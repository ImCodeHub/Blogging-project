package com.Blogging.Platform.Blog.Model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogPost {
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;
}

