package com.Blogging.Platform.Blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Blogging.Platform.Blog.Exception.CustomException.AuthorNotFoundException;
import com.Blogging.Platform.Blog.Exception.CustomException.EmailAlreadyExistException;
import com.Blogging.Platform.Blog.Exception.CustomException.InvalidEmailException;
import com.Blogging.Platform.Blog.Exception.CustomException.PostNotFoundException;

// to hange the exception
@ControllerAdvice
public class PostExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<?> handlePostNotFoundException(PostNotFoundException ex, WebRequest webRequest){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<?> handleAuthorNotFoundException(AuthorNotFoundException ex, WebRequest webRequest){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException ex, WebRequest webRequest){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> handleEmailAlreadyExistException(EmailAlreadyExistException ex, WebRequest webRequest){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }   
}
