package com.Blogging.Platform.Blog.Exception;

public class CustomException {
    public static class PostNotFoundException extends RuntimeException{
        public PostNotFoundException(String message){
            super(message);
        }
    }
    public static class InvalidEmailException extends RuntimeException{
        public InvalidEmailException(String message){
            super(message);
        }
    }
    public static class EmailAlreadyExistException extends RuntimeException{
        public EmailAlreadyExistException(String message){
            super(message);
        }
    }
}
