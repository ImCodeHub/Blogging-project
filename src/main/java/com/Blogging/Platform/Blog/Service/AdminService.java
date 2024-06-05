package com.Blogging.Platform.Blog.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import logger from slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blogging.Platform.Blog.Entity.Post;
import com.Blogging.Platform.Blog.Entity.Role;
import com.Blogging.Platform.Blog.Entity.User;
import com.Blogging.Platform.Blog.Exception.CustomException.PostNotFoundException;
import com.Blogging.Platform.Blog.Exception.CustomException.AuthorNotFoundException;
import com.Blogging.Platform.Blog.Model.UserBlogPost;
import com.Blogging.Platform.Blog.Model.UserListModel;
import com.Blogging.Platform.Blog.Repository.PostRepository;
import com.Blogging.Platform.Blog.Repository.UserRepository;
import com.Blogging.Platform.Blog.Service.PostServiceImpl.AdminServiceImpl;

@Service
public class AdminService implements AdminServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserBlogPost> getAllPost() {
        List<UserBlogPost> list = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        if (posts != null) {
            for (Post post : posts) {
                UserBlogPost blogPost = new UserBlogPost();
                blogPost.setId(post.getId());
                blogPost.setTitle(post.getTitle());
                blogPost.setAuthor(post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName());

                blogPost.setContent(post.getContent());
                blogPost.setCreatedAt(post.getCreatedAt());

                list.add(blogPost);
            }
            logger.info("All post data retrive from table");
            return list;
        } else {
            logger.error("No post found from the Post table");
            throw new PostNotFoundException("No post found in the list.");
        }
    }

    @Override
    public List<UserListModel> getAllAuthor() {
        List<UserListModel> list = new ArrayList<>();
        // List<User> users = userRepository.findByRole(Role.MEMBER);
        List<Object[]> users = userRepository.findAllAuthorsWithPostCountNative();
        logger.info("user details received {}", users);
        if (users != null) {
            for (Object[] user : users) {
                UserListModel userListModel = new UserListModel(
                        (Integer) user[0], user[1].toString(), user[2].toString(), user[3].toString());

                // userListModel.setId(user.getId());
                // userListModel.setUserName(user.getFirstName()+" "+user.getLastName());
                // userListModel.setUserEmail(user.getEmail());

                list.add(userListModel);
            }
            logger.info("return the user list from ");
            return list;
        } else {
            logger.error("User details list not found");
            throw new AuthorNotFoundException("Author list not found");
        }
    }

    @Override
    public Boolean deletePost(Integer postId) {
        Optional<Post> optional = postRepository.findById(postId);
        if (optional.isPresent()) {
            postRepository.deleteById(postId);
            return true;
        } else {
            throw new PostNotFoundException("Post not found by this id: " + postId);
        }
    }

    // @Override
    // public Boolean deleteAuthor(Integer authorId) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'deleteAuthor'");
    // }

}
