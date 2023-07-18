package com.k1rard.app.entities;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(Long postId) {
        super(String.format("post id:%s not found!", postId));
    }
}
