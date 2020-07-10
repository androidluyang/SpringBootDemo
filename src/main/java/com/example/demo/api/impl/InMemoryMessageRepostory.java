package com.example.demo.api.impl;

import com.example.demo.api.MessageRepostory;
import com.example.demo.entity.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Service("messageRepository")
public class InMemoryMessageRepostory implements MessageRepostory {

    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentHashMap<Long,Message> messages = new ConcurrentHashMap<>();

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>(this.messages.values());
        return messages;
    }

    @Override
    public Message save(Message message) {
        Long id = message.getId();
        if(id==null){
            id = counter.incrementAndGet();
            message.setId(id);
        }
        this.messages.put(id,message);
        return message;
    }

    @Override
    public Message update(Message message) {
        this.messages.put(message.getId(),message);
        return message;
    }

    @Override
    public Message updateText(Message message) {
        Message msg = this.messages.get(message.getId());
        msg.setContent(message.getContent());
        this.messages.put(msg.getId(),msg);
        return msg;
    }

    @Override
    public Message findMessage(Long id) {
        Message msg = this.messages.get(id);
        return msg;
    }

    @Override
    public void deleteMessage(Long id) {
        this.messages.remove(id);
    }
}
