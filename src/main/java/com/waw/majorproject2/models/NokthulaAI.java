package com.waw.majorproject2.models;


import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class NokthulaAI  {
    @Id
    @SequenceGenerator(name = "ai_generator", sequenceName = "ai_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ai_generator")
    private Long id;

    @OneToOne
    private WawUser owner;

    @OneToMany
    private List<TextMessage> thread;



    public NokthulaAI() {
        this.thread = new ArrayList<>();
    }
    public String sayToNokthula(TextMessage message) throws IOException {
        try (VertexAI vertexAi = new VertexAI("waworganics", "us-central1"); ) {
            GenerationConfig generationConfig =
                    GenerationConfig.newBuilder()
                            .setMaxOutputTokens(8192)
                            .setTemperature(1F)
                            .setTopP(0.95F)
                            .build();
            List<SafetySetting> safetySettings = Arrays.asList(
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build()
            );
            GenerativeModel model =
                    new GenerativeModel.Builder()
                            .setModelName("gemini-1.5-flash-002")
                            .setVertexAi(vertexAi)
                            .setGenerationConfig(generationConfig)
                            .setSafetySettings(safetySettings)
                            .build();


            if(message.getUserSaid().isEmpty())
                return "Please say somthing.";
            var content = ContentMaker.fromMultiModalData(message.getUserSaid()+"! less that 2000 short as possible ...good answer for whatsapp text. ");
            var response = model.generateContent(content);
            String answer = response.getCandidates(0).getContent().getParts(0).getText();

            return answer;
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
