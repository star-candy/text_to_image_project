package org.example.model;

import java.io.IOException;
import org.example.dto.HuggingfaceRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class PromptToImageManagerTest {

    @Test
    public void testImage() throws IOException {
        String prompt = "a cat wearing a hat";
        PromptToImageManager manager = new PromptToImageManager(prompt);
        manager.requestHuggingface();
        manager.downloadImage();
    }

}

