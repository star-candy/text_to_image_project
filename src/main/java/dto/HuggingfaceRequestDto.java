package dto;

import okhttp3.*;

public class HuggingfaceRequestDto {
    private static final String API_URL = "https://api-inference.huggingface.co/models/black-forest-labs/FLUX.1-dev"; // FLUX 모델 URL
    private static final String API_KEY = "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";  // Hugging Face API 키
    private final String prompt;

    public HuggingfaceRequestDto(String prompt) {
        this.prompt = prompt;
    }

    public Request generateJsonRequest() {

        //세부 출력 사항 api 문서 요청 페이로드 항목 참고 https://huggingface.co/docs/api-inference/tasks/text-to-image?code=js
        String json = String.format("{\"inputs\": \"%s\", \"parameters\": {\"num_inference_steps\": 50, \"guidance_scale\": 7.5}}", prompt);
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json")); //json 형식 데이터 전송 명시

        return new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();
    }
}
