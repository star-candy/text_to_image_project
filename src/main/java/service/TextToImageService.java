package service;
import model.PromptToImageManager;
import model.TextToPromptManager;
public class TextToImageService {
    private final TextToPromptManager textToPromptManager;
    private final PromptToImageManager promptToImageManager;

    public TextToImageService() {
        this.textToPromptManager = new TextToPromptManager();
        this.promptToImageManager = new PromptToImageManager();
    }

    public void convertToImage(String inputText) {
        String prompt = textToPromptManager.convertToPrompt(inputText);
        if(prompt == null) {
            System.out.println("Error! 프롬프트 생성에 실패했습니다.");
            return;
        }
        promptToImageManager.downloadImage(prompt);
    }
}
