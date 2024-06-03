package com.Blogging.Platform.Blog.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogPost {
    private String title;

    @JsonIgnore
    private String author;
    private String content;
    private LocalDateTime createdAt;
}

