package com.waw.majorproject2.models;



import com.waw.majorproject2.records.ModelPlantListResponse;
import com.waw.majorproject2.records.ModelPlantResponse;
import jakarta.persistence.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.util.MimeTypeUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Entity
public  class NokthulaAI  {
    @Id
    @SequenceGenerator(name = "ai_generator", sequenceName = "ai_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ai_generator")
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    private WawUser owner;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<TextMessage> thread;

    public NokthulaAI() {
        this.thread = new ArrayList<>();

    }

//Saying something to nokthula
    public String sayToNokthula(TextMessage message, ChatClient chatClient){
        StringBuilder threadString = new StringBuilder();
        for(TextMessage t: thread){
            threadString.append(":user said").append(t.getUserSaid());
            threadString.append(":you said").append(t.getNokthulaSaid());
        }
        if(message.getUserSaid().isEmpty())
            return "Please say something.";
        return chatClient.prompt()
                .system(systemMessage -> systemMessage
                        .text("You are Nokthula Nsimini from Eswatini, Sidwashini. working as an AI assistant on the Waw Organics mobile application")
                        .text( "The users name is " + owner.getFirstName())
                        .text("The users last name is"  + owner.getLastName())
                        .text("The users last name is " + owner.getLastName())
                        .text("These are messages of your chat history with the user" + threadString)
                )
                .user(
                        userMessage -> userMessage
                                .text(message.getUserSaid())
                )
                .call()
                .content();

    }
    //get 10 popular farms in swaziland
    public List<Crop> popularCropsInSwaziland(ChatClient chatClient){
        String message ="List 10 of the most common Crops for farming in swaziland";
        PromptTemplate promptTemplate = new PromptTemplate(message);
        Prompt prompt = promptTemplate.create();
        return chatClient.prompt(prompt)
                .call()
                .entity(ModelPlantListResponse.class)
                .plants();
    }

    //asking to get the defective crops
    public static Crop getCropDefects(InputStream image, ChatClient chatClient){
        ModelPlantResponse modelListResponse = chatClient.prompt()
                .system(systemMessage -> systemMessage
                        .text("You are Nokthula Nsimini from Eswatini, Sidwashini. working as an AI assistant on the Waw Organics mobile application")
                        .text("You are and Expert Assistant able to analyse images working in the WawOrganics Mobile App.")
                )
                .user(
                        systemMessage -> systemMessage
                                .text("Please make sure you find out what plant is called and fill in the cropName of the picture and fill try  its other details")
                                .text("Please find out and fill the crop defects you see on the crop")
                                .text("Find the crop details crop defects and the various natural remedies for the defects")
                                .media(MimeTypeUtils.IMAGE_PNG, new InputStreamResource(image))
                )
                .call()
                .entity(ModelPlantResponse.class);

        return modelListResponse.plant();
    }

    //welcoming the user through a notification.
    public TextMessage welcomeUser(ChatClient chatClient){
        try {
            String threadString = "";
            for (TextMessage t : thread) {
                threadString = threadString + ":user said" + t.getUserSaid();
                threadString = threadString + ":you said" + t.getNokthulaSaid();
            }

            TextMessage textMessage = new TextMessage();
            String finalThreadString = threadString;
            textMessage.setNokthulaSaid(chatClient.prompt()
                    .system(systemMessage -> systemMessage
                            .text("You are Nokthula Nsimini from Eswatini, Sidwashini. working as an AI assistant on the Waw Organics mobile application")
                            .text( "The users name is " + owner.getFirstName())
                            .text("The users last name is"  + owner.getLastName())
                            .text("The users last name is " + owner.getLastName())
                            .text("This is your chat history with the user" + finalThreadString)
                            .text("Welcome the user to the application." )
                    )
                    .call()
                    .content());
            return textMessage;
        }catch (Exception e){
            TextMessage textMessage = new TextMessage();
            textMessage.setNokthulaSaid("Welcome to Waw Organics "+owner.firstName);
            return textMessage;
        }
    }


    public NokthulaAI(Long id, WawUser owner, List<TextMessage> thread) {
        this.id = id;
        this.owner = owner;
        this.thread = thread;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WawUser getOwner() {
        return owner;
    }

    public void setOwner(WawUser owner) {
        this.owner = owner;
    }

    public List<TextMessage> getThread() {
        return thread;
    }

    public void setThread(List<TextMessage> thread) {
        this.thread = thread;
    }


}
