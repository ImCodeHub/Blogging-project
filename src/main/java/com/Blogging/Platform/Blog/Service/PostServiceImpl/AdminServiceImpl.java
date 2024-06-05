package com.Blogging.Platform.Blog.Service.PostServiceImpl;

import java.util.List;

import com.Blogging.Platform.Blog.Model.UserBlogPost;
import com.Blogging.Platform.Blog.Model.UserListModel;

public interface AdminServiceImpl {
    public List<UserBlogPost> getAllPost();
    public List<UserListModel> getAllAuthor();
    public Boolean deletePost(Integer postId);
    // public Boolean deleteAuthor(Integer authorId);
}
