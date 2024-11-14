package org.example.model;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class PromptToImageManagerTest {

    private final PromptToImageManager manager = new PromptToImageManager();

    @Test
    public void testImage1() {
        String prompt = "a hat on a cat";
        manager.downloadImage(prompt);
        while(true);
    }

    @Test
    public void testImage2() {
        String prompt = "a dog in the space";
        manager.downloadImage(prompt);
        while(true);
    }
}