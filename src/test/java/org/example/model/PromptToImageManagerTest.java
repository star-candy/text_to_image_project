package org.example.model;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class PromptToImageManagerTest {

    private final PromptToImageManager manager = new PromptToImageManager();

    @Test
    public void testImage1() {
        String prompt = "a dog sitting on a couch";
        try {
            manager.downloadImage(prompt);
        } catch (IOException e) {
            System.out.println("1실패: " + e.getMessage());
        }
    }

    @Test
    public void testImage2() {
        String prompt = "a cat walking on sunset";
        try {
            manager.downloadImage(prompt);
        } catch (IOException e) {
            System.out.println("2실패: " + e.getMessage());
        }
    }
}