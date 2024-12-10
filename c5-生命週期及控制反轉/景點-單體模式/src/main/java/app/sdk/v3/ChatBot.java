package app.sdk.v3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

/**
 * ChatBot Singleton
 */
public class ChatBot {
    public static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final static String endpoint = "https://api.openai.com/v1/completions";
    private static final String MODEL = "text-davinci-003";
    private static final BigDecimal DAVINCI_MODEL_PRICE_PER_TOKEN = BigDecimal.valueOf(0.02).divide(BigDecimal.valueOf(1000), RoundingMode.CEILING); // USD
    private static final int MAX_TOKENS = 160;
    private static final int TEMPERATURE = 0;
    private static final Path remainingBudgetPath = Paths.get("remaining-budget");
    private final String secret;

    private final static ChatBot instance = new ChatBot();

    public static ChatBot getInstance() {
        return instance;
    }

    private ChatBot() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResource("secret").openStream();
            secret = new BufferedReader(new InputStreamReader(in))
                    .lines().parallel().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String chat(String message) {
        try {
            if (hasRemainingBudget()) {
                String requestBody = format("{\"model\": \"%s\",\n  \"prompt\": \"接下來是一個與 AI 的對話，AI 很幽默、自信有專業又有個性。\\n\\n人類：%s\", \"max_tokens\": %d,\n  \"temperature\": %d\n}", MODEL, message, MAX_TOKENS, TEMPERATURE);
                HttpRequest request = chatApiRequest(requestBody);
                HttpResponse<String> response = sendRequest(request);
                Response res = MAPPER.readValue(response.body(), Response.class);
                updateRemainingBudget(res);
                return aggregateResponseText(res);
            }
            throw new IllegalStateException("Under no budget!");
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasRemainingBudget() throws IOException {
        return parseLong(Files.readString(remainingBudgetPath)) > 0;
    }

    private void updateRemainingBudget(Response res) throws IOException {
        BigDecimal budget = BigDecimal.valueOf(parseLong(Files.readString(remainingBudgetPath)));
        long tokenSum = res.choices.stream().mapToLong(c -> c.totalTokens).sum();
        BigDecimal cost = BigDecimal.valueOf(tokenSum).multiply(DAVINCI_MODEL_PRICE_PER_TOKEN);
        BigDecimal remainingBudget = budget.subtract(cost);
        Files.writeString(remainingBudgetPath, String.valueOf(remainingBudget.longValue()));
    }


    private HttpRequest chatApiRequest(String requestBody) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(endpoint))
                .POST(BodyPublishers.ofString(requestBody))
                .header("Authorization", format("Bearer %s", secret))
                .header("Content-Type", "application/json")
                .build();
    }

    private static HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String aggregateResponseText(Response res) {
        return res.choices.stream()
                .map(c -> c.text)
                .collect(joining("\n"));
    }

    public static class Response {
        public String id;
        public String object;
        public long created;
        public List<Choice> choices;
    }

    public static class Choice {
        public String text;
        public int index;
        @JsonProperty("finish_reason")
        public String finishReason;
        @JsonProperty("completion_tokens")
        public long completionTokens;
        @JsonProperty("total_tokens")
        public long totalTokens;
    }


    public static void main(String[] args) throws IOException {
        System.out.println(new ChatBot().chat("Hi"));
    }
}
