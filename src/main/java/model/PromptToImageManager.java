package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import dto.HuggingfaceRequestDto;
import org.jetbrains.annotations.NotNull;

public class PromptToImageManager {
    private static int OUTPUT_IMAGE_NUM = 0;
    private static final String userHome = System.getProperty("user.home");
    private static final String downloadDir = userHome + "\\Downloads";
    private final OkHttpClient client;

    public PromptToImageManager() {
        // OkHttpClient timeout 설정 - 이미지 생성 응답 2분정도 소요 될 것
        this.client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();
    }

//기존 구현 방식은 이미지 생성 중 다른 동작 수행 불가 문제, 따라서 비동기 방식으로 변경하여 해결
public void downloadImage(String prompt) {//huggingface 통신 및 이미지 생성 메서드 하나로 통합
    OUTPUT_IMAGE_NUM++;
    System.out.println("이미지 생성 중 : 2분내로 완료됩니다");
    HuggingfaceRequestDto huggingfaceRequestDto = new HuggingfaceRequestDto(prompt);
    Request request = huggingfaceRequestDto.generateJsonRequest();
    String outputPath = downloadDir + "/output_image" + OUTPUT_IMAGE_NUM + ".png";

    // 비동기 요청 실행
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) { //경고로 인한 notnull 추가
            System.out.println("이미지 다운로드 실패: " + e.getMessage());
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            if (!response.isSuccessful()) {
                System.out.println("이미지 다운로드 실패: " + response);
                return;
            }
            try (FileOutputStream fos = new FileOutputStream(outputPath)) { //여러번 호출을 요구할 경우 1회 이후부터 사용 불가 문제 해결위한 try-with-resources 사용
                fos.write(response.body().bytes()); //이미지 바이트 배열 파일로 저장 확인함
                System.out.println("다운로드 완료: " + outputPath);
            }
        }
    });
}
}