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
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();
    }

    // 비동기 방식으로 이미지를 다운로드하는 메서드
    public void downloadImage(String prompt) {
        OUTPUT_IMAGE_NUM++;
        HuggingfaceRequestDto huggingfaceRequestDto = new HuggingfaceRequestDto(prompt);
        Request request = huggingfaceRequestDto.generateJsonRequest();
        String outputPath = downloadDir + "/output_image" + OUTPUT_IMAGE_NUM + ".png";

        // 비동기 요청 실행
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("이미지 다운로드 실패: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    System.out.println("이미지 다운로드 실패: " + response);
                    return;
                }
                try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                    fos.write(response.body().bytes());
                    System.out.println("다운로드 완료: " + outputPath);
                }
            }
        });
    }
}