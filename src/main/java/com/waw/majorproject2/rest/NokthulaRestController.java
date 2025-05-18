package com.waw.majorproject2.rest;

import com.waw.majorproject2.models.*;
import com.waw.majorproject2.repositories.CropImageRepository;
import com.waw.majorproject2.repositories.NokthulaAIRepository;
import com.waw.majorproject2.repositories.TextMessageRepository;
import com.waw.majorproject2.repositories.WawUsersRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@RestController
public class NokthulaRestController {

    @Autowired
    TextMessageRepository textMessageRepository;
    @Autowired
    NokthulaAIRepository nokthulaAIRepository;

    @Autowired
    WawUsersRepository wawUsersRepository;
    @Autowired
    CropImageRepository cropImageRepository;

    @Autowired
    NokthulaAI nokthulaAI;

    WawUser wawUser;

    @NotNull

    @Autowired
    private ChatClient chatClient;


    @GetMapping("/api/detectDefects/{photoId}")
    public ResponseEntity<Crop> getCropDefects(@PathVariable Long photoId) {
        if(cropImageRepository.findById(photoId).isPresent()) {
            CropImage cropImage = cropImageRepository.findById(photoId).get();
            return ResponseEntity.ok(NokthulaAI.getCropDefects(cropImage.base64ToInputStream(), chatClient));
        }
        return ResponseEntity.ok(new Crop());
    }

    @GetMapping("/api/nokthula/welcome/{id}")
    public ResponseEntity<TextMessage> welcome(@PathVariable Long id) {
        nokthulaAI = getAssistant(id).getBody();
        try{
            assert nokthulaAI!=null;
            return ResponseEntity.ok(nokthulaAI.welcomeUser(chatClient));
        }catch (Exception e){
            TextMessage textMessage = new TextMessage();
            textMessage.setNokthulaSaid("Welcome to WawOrganics");
            return ResponseEntity.ok(textMessage);
        }
    }


    @GetMapping("/api/nokthula/get/{id}")
    public ResponseEntity<NokthulaAI> getAssistant(@PathVariable Long id) {
        for (NokthulaAI nokthulaAI : nokthulaAIRepository.findAll()) {
            if (nokthulaAI.getOwner().getId().equals(id)) {
                this.nokthulaAI = nokthulaAI;

                //get latest
                if (nokthulaAI.getThread().isEmpty())
                    return ResponseEntity.ok(nokthulaAI);
                if (nokthulaAI.getThread().size() < 13)
                    return ResponseEntity.ok(nokthulaAI);
                nokthulaAI.setThread(nokthulaAI.getThread().subList(nokthulaAI.getThread().size() - 13, nokthulaAI.getThread().size()));
                return ResponseEntity.ok(nokthulaAI);
            }
        }
        return null;
    }

    @GetMapping("/api/nokthula/chat/{msg}")
    public ResponseEntity<TextMessage> organicExpertConversation(@PathVariable String msg) {
        //update thread
        System.out.println(nokthulaAI.getOwner().getFirstName());

        System.out.println(msg);
        TextMessage textMessage = new TextMessage();
        textMessage.setUserSaid(msg);
        textMessage.setNokthulaSaid(nokthulaAI.sayToNokthula(textMessage, chatClient));
        textMessage = textMessageRepository.save(textMessage);
        nokthulaAI.getThread().add(textMessage);
        nokthulaAI = nokthulaAIRepository.save(nokthulaAI);
        return ResponseEntity.ok(textMessage);
    }




    @GetMapping("api/nokthula/create/{id}")
    public ResponseEntity<NokthulaAI> createNokthulaAI(@PathVariable Long id) {
        NokthulaAI newNokthulaAI = new NokthulaAI();
        newNokthulaAI.setThread(new ArrayList<>());
        WawUser wawUser = wawUsersRepository.findById(id).get();
        newNokthulaAI.setOwner(wawUser);
        newNokthulaAI = nokthulaAIRepository.save(newNokthulaAI);
        return ResponseEntity.ok(newNokthulaAI);

    }

    //AI features
    @GetMapping("api/nokthula/getPopularCrops")
    public ResponseEntity<List<Crop>> getPopularCrops(){
        return ResponseEntity.ok(nokthulaAI.popularCropsInSwaziland(chatClient));
    }
}

