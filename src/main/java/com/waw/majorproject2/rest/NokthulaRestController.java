package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.NokthulaAI;
import com.waw.majorproject2.services.NokhtulaAIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NokthulaRestController {
    private final ChatClient chatClient;
    @Autowired
    NokhtulaAIService nokhtulaAIService;

    public NokthulaRestController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/api/nokthula/{chat}")
    public ResponseEntity<String> OrganicExpert(@PathVariable String chat){
        return ResponseEntity.ok( chatClient.prompt()
                .user(chat)
                .call()
                .content());
    }
    @GetMapping("/api/nokthula")
    public ResponseEntity<String> OrganicExpert(){
        return ResponseEntity.ok( chatClient.prompt()
                .user("hi ")
                .call()
                .content());
    }

    @GetMapping("api/nokthula/get/{id}")
    public ResponseEntity<NokthulaAI> getNokthulaAI(@PathVariable Long id){
        return ResponseEntity.ok(nokhtulaAIService.getNokthulaAI(id));
    }
}

