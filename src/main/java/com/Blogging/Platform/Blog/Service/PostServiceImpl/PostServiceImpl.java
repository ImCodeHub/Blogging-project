package com.Blogging.Platform.Blog.Service.PostServiceImpl;

import java.util.List;

import com.Blogging.Platform.Blog.Entity.User;
import com.Blogging.Platform.Blog.Model.BlogPost;
import com.Blogging.Platform.Blog.Model.UserBlogPost;

public interface PostServiceImpl {
    public String createPost(BlogPost blogPost, User user);
    // public List<Post> getAllPost();
    public List<UserBlogPost> getPostByAuthor(Integer authorId,String author);
    public List<UserBlogPost> findPostById(Integer postId, Integer authorId);
}
