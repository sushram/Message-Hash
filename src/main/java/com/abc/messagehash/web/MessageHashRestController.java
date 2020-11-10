package com.abc.messagehash.web;

import com.abc.messagehash.buisness.service.MessageHashService;
import com.abc.messagehash.buisness.service.RequestCounter;
import com.abc.messagehash.data.entity.MessageHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageHashRestController {

    private final MessageHashService service;

    private final RequestCounter counter;

    @Autowired
    public MessageHashRestController(MessageHashService service,RequestCounter counter) {
        this.service = service;
        this.counter = counter;
    }


    @PostMapping
    public ResponseEntity<MessageCreated> createMessage(@RequestBody Message message) {
        counter.increment("POST /messages");
        String digest = service.createMessageHash(message.getMessage());

        return new ResponseEntity<>(new MessageCreated(digest),HttpStatus.OK);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Message> getMessage(@PathVariable String hash) throws Exception {
            counter.increment("GET /messages/<hash>");
            MessageHash messageByHash = service.getMessageByHash(hash);
            return new ResponseEntity<>(new Message(messageByHash.getMessage()),HttpStatus.OK);

    }

    @DeleteMapping("/{hash}")
    public void deleteMessage(@PathVariable String hash) throws Exception {
        counter.increment("DELETE /messages/<hash>");
        MessageHash messageByHash = service.getMessageByHash(hash);
        service.deleteMessageByHash(messageByHash);
    }


}
