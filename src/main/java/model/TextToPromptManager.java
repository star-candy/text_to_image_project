package model;

/*  필요한 패키지들을 import
Gson 라이브러리의 클래스들을 import
OkHttpClient 라이브러리의 클래스들을 import
IOException 클래스를 import 
 */
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import okhttp3.*;
import com.google.gson.JsonParser;
import java.io.IOException;

public class TextToPromptManager {

    // 기존모델 gpt2에 한국어를 영어로 변역하고 이미지를 생성하기위한 설명문으로 바꾸는것을 요청했으나 모델의 한계로 예상외의 출력들이 많이나옴
    // 단순히 한글을 영어로 바꾸는 모델로 변경.
    private static final String API_URL = "https://api-inference.huggingface.co/models/Helsinki-NLP/opus-mt-ko-en";
    private static final String API_KEY = "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"; // API 키

    // OkHttpClient 객체 생성
    static OkHttpClient client = new OkHttpClient();

    // 입력 텍스트를 프롬프트로 변환하는 메서드
    public String convertToPrompt(String inputText) {
        // 요청 바디 생성
        JsonObject json = new JsonObject();
        // 이전에는 입력의 조건을 넣었으나 모델의 한계로 인해 단순히 번역만 요청
        json.addProperty("inputs",inputText );

        // 요청 바디 생성
        RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
        
        // API 요청 생성
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        // 응답 처리
        try (Response response = client.newCall(request).execute()) {
            // 응답이 성공적이지 않은 경우 예외 처리
            if (!response.isSuccessful()) {
                throw new IOException("API call failed: " + response.code() + " " + response.message());
            }

            // 응답 바디를 문자열로 변환
            String responseBody = response.body().string();
            // 응답 바디에서 생성된 텍스트 추출
            return parseResponse(responseBody);
        // 예외 처리
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    // 응답 바디에서 생성된 텍스트 추출하는 메서드
    private static String parseResponse(String responseBody) {
        try {
            JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            return jsonObject.get("translation_text").getAsString().trim();
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }
}
