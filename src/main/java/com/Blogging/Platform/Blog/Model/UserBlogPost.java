package com.Blogging.Platform.Blog.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogPost {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String author;
    
    private String content;
    
    private LocalDateTime createdAt;
}

