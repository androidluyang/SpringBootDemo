package com.example.demo.api;

import com.example.demo.entity.Message;

import java.util.List;

public interface MessageRepostory {
    public List<Message> findAll();
    public Message save(Message message);
    public Message update(Message message);
    public Message updateText(Message message);
    public Message findMessage(Long id);
    public void deleteMessage(Long id);
}
