package com.obcompany.simplecrud.controllers;

import com.obcompany.simplecrud.configuration.RedisMessagePublisher;
import com.obcompany.simplecrud.configuration.RedisMessageSubscriber;
import com.obcompany.simplecrud.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisMessagePublisher messagePublisher;


    @PostMapping("/publish")
    public void publish(@RequestBody Message message) {
        logger.info(">> publishing : {}", message);
        messagePublisher.publish(message.toString());
    }

    @GetMapping("/subscribe")
    public List<String> getMessages(){
        return RedisMessageSubscriber.messageList;
    }

}