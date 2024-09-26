package org.example.model;

import java.util.concurrent.TimeUnit;
import okhttp3.*;
import java.io.FileOutputStream;
import java.io.IOException;
import org.example.dto.HuggingfaceRequestDto;

public class PromptToImageManager {
    private static int OUTPUT_IMAGE_NUM = 0;
    private static final String userHome = System.getProperty("user.home");
    private static final String downloadDir = userHome + "\\Downloads";
    private final String prompt;
    private Request request;

    PromptToImageManager(String prompt) {
        OUTPUT_IMAGE_NUM++;
        this.prompt = prompt;
    }

    public void requestHuggingface() {
        HuggingfaceRequestDto huggingfaceRequestDto = new HuggingfaceRequestDto(prompt);
        request = huggingfaceRequestDto.generateJsonRequest();
    }

    public void downloadImage() throws IOException {
        // OkHttpClient timeout 설정 - 이미지 생성 응답 2분정도 소요 될 것
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃
                .writeTimeout(30, TimeUnit.SECONDS) // 쓰기 타임아웃
                .readTimeout(120, TimeUnit.SECONDS) // 읽기 타임아웃
                .build();

        Response response = client.newCall(request).execute();
        String outputPath = downloadDir + "/output_image" + OUTPUT_IMAGE_NUM + ".png";

        FileOutputStream fos = new FileOutputStream(outputPath);
        fos.write(response.body().bytes()); //이미지 바이트 배열 파일로 저장
    }
}
