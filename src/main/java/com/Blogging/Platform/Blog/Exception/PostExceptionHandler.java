package com.Blogging.Platform.Blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.Blogging.Platform.Blog.Exception.CustomException.PostNotFoundException;

@ControllerAdvice
public class PostExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> handlePostNotFoundException(PostNotFoundException ex, WebRequest webRequest){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    
}
