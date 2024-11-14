package org.example.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import org.example.dto.HuggingfaceRequestDto;

public class PromptToImageManager {
    private static int OUTPUT_IMAGE_NUM = 0;
    private static final String userHome = System.getProperty("user.home");
    private static final String downloadDir = userHome + "\\Downloads";
    private final OkHttpClient client;

    public PromptToImageManager() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(150, TimeUnit.SECONDS)
                .build();
    }

    // prompt를 받아 매번 새로운 요청을 생성하고 이미지를 다운로드하는 메서드
    public void downloadImage(String prompt) throws IOException {
        OUTPUT_IMAGE_NUM++;
        // 새로운 request 객체 생성
        HuggingfaceRequestDto huggingfaceRequestDto = new HuggingfaceRequestDto(prompt);
        Request request = huggingfaceRequestDto.generateJsonRequest();
        String outputPath = downloadDir + "/output_image" + OUTPUT_IMAGE_NUM + ".png";

        // 요청을 실행하고 응답을 처리
        try (Response response = client.newCall(request).execute();
             FileOutputStream fos = new FileOutputStream(outputPath)) {
            if (!response.isSuccessful()) {
                throw new IOException("Image download failed: " + response);
            }
            fos.write(response.body().bytes());
            System.out.println("다운로드 완료: " + outputPath);
        }
    }
}