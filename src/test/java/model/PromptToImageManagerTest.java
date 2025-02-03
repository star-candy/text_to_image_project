package model;

import org.junit.jupiter.api.Test;

public class PromptToImageManagerTest {

    private final PromptToImageManager manager = new PromptToImageManager();

    @Test
    public void testImage1() {
        String prompt = "a hat on a cat";
        String prompt1 = "a hat on a dog";
        String prompt2 = "a hat on a bird";
        manager.downloadImage(prompt);
        manager.downloadImage(prompt1);
        manager.downloadImage(prompt2);
        while (true);

    }
}


