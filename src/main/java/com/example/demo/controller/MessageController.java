package com.example.demo.controller;


import com.example.demo.api.MessageRepostory;
import com.example.demo.entity.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "11111",description = "消息操作 API")
@RestController
@RequestMapping("/")
public class MessageController {
    @Autowired
    private MessageRepostory messageRepostory;

    @ApiOperation(value = "消息列表",notes="获取完整的消息列表")
    @GetMapping(value = "messages")
    public List<Message> list(){
        List<Message> messages = this.messageRepostory.findAll();
        return messages;
    }

    @ApiOperation(value = "添加消息", notes = "根据参数创建消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "正⽂", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁⽌访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误")
    })
    @PostMapping(value = "message")
    public Message create(Message message){
        message = this.messageRepostory.save(message);
        return message;
    }

}
