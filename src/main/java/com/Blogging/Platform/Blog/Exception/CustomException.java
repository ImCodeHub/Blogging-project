package com.Blogging.Platform.Blog.Exception;

// To handle the exception.
public class CustomException {

    // if post not found.
    public static class PostNotFoundException extends RuntimeException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }

    // if author not found.
    public static class AuthorNotFoundException extends RuntimeException {
        public AuthorNotFoundException(String message) {
            super(message);
        }
    }

    // if invalid email found.
    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    // if email is already exist.
    public static class EmailAlreadyExistException extends RuntimeException {
        public EmailAlreadyExistException(String message) {
            super(message);
        }
    }
}
