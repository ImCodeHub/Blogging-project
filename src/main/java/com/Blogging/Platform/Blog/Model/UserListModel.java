package com.Blogging.Platform.Blog.Model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListModel {
    private Integer id;
    private String userName;
    private String userEmail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String blogCount;
}
