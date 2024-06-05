package com.Blogging.Platform.Blog.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blogging.Platform.Blog.Entity.Post;
import com.Blogging.Platform.Blog.Entity.User;
import com.Blogging.Platform.Blog.Exception.CustomException.PostNotFoundException;
import com.Blogging.Platform.Blog.Model.BlogPost;
import com.Blogging.Platform.Blog.Model.UserBlogPost;
import com.Blogging.Platform.Blog.Repository.PostRepository;
import com.Blogging.Platform.Blog.Service.PostServiceImpl.PostServiceImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostService implements PostServiceImpl {
    @Autowired
    private PostRepository postRepository;

    @Override
    public String createPost(BlogPost blogPost, User user) {
        Post post = new Post();
        if (blogPost != null) {
            // set user in auth
            post.setAuthor(user);
            post.setTitle(blogPost.getTitle());
            post.setContent(blogPost.getContent());

            postRepository.save(post);

            return "Blog Post details saved successfully.";

        }
        throw new RuntimeException("unable to save the details of blog post.");
    }

    @Override
    public List<UserBlogPost> getPostByAuthor(Integer authorId, String authorName) {
        List<UserBlogPost> postList = new ArrayList<>();
        List<Post> posts = postRepository.findByAuthorId(authorId);
        if(authorId != null){
            for(Post post : posts){
                UserBlogPost userBlogPost = new UserBlogPost();
                userBlogPost.setTitle(post.getTitle());
                userBlogPost.setContent(post.getContent());
                userBlogPost.setCreatedAt(post.getCreatedAt());
                
                // userBlogPost.setAuthor(authorName);
                postList.add(userBlogPost);
            }
            return postList;
        }else{

            throw new PostNotFoundException("No blog Post found");
        }
        
    }

    @Override
    public List<UserBlogPost> findPostById(Integer postId, Integer authorId) {
        List<UserBlogPost> list = new ArrayList<>();
        Optional<Post> optional = postRepository.findPostByIdAndAuthor_Id(postId,authorId);
        if(optional.isPresent()){
            Post post = optional.get();

            UserBlogPost blog = new UserBlogPost();

            blog.setTitle(post.getTitle());
            blog.setContent(post.getContent());
            blog.setCreatedAt(post.getCreatedAt());

            list.add(blog);

            return list;
        }else{
            throw new PostNotFoundException("No Blog Post found By this Id");
        }
    }

    @Override
    public String updatePost(Integer postId, BlogPost blogPost, Integer authorId) {
        Optional<Post> optional = postRepository.findPostByIdAndAuthor_Id(postId, authorId);
        if(optional.isPresent()){
            Post post = optional.get();

            post.setTitle(blogPost.getTitle());
            post.setContent(blogPost.getContent());

            postRepository.save(post);

            return "Blog post details has been updated for: "+postId;

        }else{
            throw new PostNotFoundException("No Blog post found by this Id to update the blog post.");
        }

    }

    @Override
    public Boolean deleteBlogPost(Integer postId, Integer authorId) {
        Optional<Post> optional = postRepository.findPostByIdAndAuthor_Id(postId,authorId);
        if(optional.isPresent()){
            postRepository.deleteById(postId);
            return true;
        }else{
            throw new PostNotFoundException("No Blog Post found By this Id: "+postId);
        }
    }    

}
