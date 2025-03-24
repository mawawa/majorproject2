package com.waw.majorproject2.rest;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.*;
import com.waw.majorproject2.models.NokthulaAI;
import com.waw.majorproject2.models.TextMessage;
import com.waw.majorproject2.models.WawUser;
import com.waw.majorproject2.repositories.NokthulaAIRepository;
import com.waw.majorproject2.repositories.TextMessageRepository;
import com.waw.majorproject2.repositories.WawUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class NokthulaRestController {

    @Autowired
    TextMessageRepository textMessageRepository;

    @Autowired
    NokthulaAIRepository nokthulaAIRepository;

    @Autowired
    WawUsersRepository wawUsersRepository;

    NokthulaAI nokthulaAI;

    WawUser wawUser;

    @GetMapping("/api/nokthula/get/{id}")
    public ResponseEntity<NokthulaAI> getAssistant(@PathVariable Long id){
        for(NokthulaAI nokthulaAI: nokthulaAIRepository.findAll()){
            if(nokthulaAI.getOwner().getId().equals(id)){
                this.nokthulaAI = nokthulaAI;
                //get latest
                nokthulaAI.setThread(nokthulaAI.getThread().subList(nokthulaAI.getThread().size()-13,nokthulaAI.getThread().size()));
                return ResponseEntity.ok(nokthulaAI);
            }
        }
        return null;
    }

    @GetMapping("/api/nokthula/chat/{msg}")
    public ResponseEntity<TextMessage> organicExpertConversation(@PathVariable String msg){
        //update thread
        System.out.println(msg);
        TextMessage textMessage = new TextMessage();
        textMessage.setUserSaid(msg);
        try {
            textMessage.setNokthulaSaid(nokthulaAI.sayToNokthula(textMessage));
        } catch (IOException e) {
            e.printStackTrace();
            textMessage.setNokthulaSaid("Nokthula is not available at the momment");
        }

        textMessage = textMessageRepository.save(textMessage);
        nokthulaAI.getThread().add(textMessage);
        nokthulaAI = nokthulaAIRepository.save(nokthulaAI);
        return  ResponseEntity.ok(textMessage);
    }


    @GetMapping ("api/nokthula/create/{id}")
    public ResponseEntity<NokthulaAI> createNokthulaAI(@PathVariable Long id){
        NokthulaAI newNokthulaAI = new NokthulaAI();
        newNokthulaAI.setThread(new ArrayList<>());
        WawUser wawUser = wawUsersRepository.findById(id).get();
        newNokthulaAI.setOwner(wawUser);
        newNokthulaAI = nokthulaAIRepository.save(newNokthulaAI);
        return ResponseEntity.ok(newNokthulaAI);

    }
}

