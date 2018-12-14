package com.electroeing.messagingjms.controllers;

import com.electroeing.messagingjms.models.Message;
import com.electroeing.messagingjms.sender.MessageSender;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@RestController
public class MainController {
    @Autowired
    MessageSender messageSender;

    @RequestMapping("/Hello")
    public String greeting() {
        Message msg = new Message("Me", "Hello World");
        messageSender.sendMessage(new Gson().toJson(msg), "test.queue");

        return "Hello World";
    }
}
