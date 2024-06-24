package com.nexusnova.lifetravelapi.app.core.tours.domain.services;

import com.theokanning.openai.DeleteResult;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.file.File;
import com.theokanning.openai.fine_tuning.FineTuningJob;
import com.theokanning.openai.fine_tuning.FineTuningJobRequest;
import com.theokanning.openai.fine_tuning.Hyperparameters;
import com.theokanning.openai.service.OpenAiService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
@Service
@ConditionalOnProperty(name = "openai.api.key")
public class OpenAIClientService {
    public static final String EMBEDDING_MODEL = "gpt-3.5-turbo-0125";
    private final OpenAiService openAiService;


    public OpenAIClientService(@Value("${openai.api.key}") String token, @Value("${openai.api.timeout.in.seconds:40}") Integer timeoutInSeconds) {
        this.openAiService = new OpenAiService(token, Duration.ofSeconds(timeoutInSeconds));
    }

    public ChatCompletionResult sendRequest(@NotNull List<ChatMessage> messages, @NotNull String model, List<String> stopReasons, @NotNull Integer maxTokens,
                                            @NotNull Double topP) {
        return openAiService
                .createChatCompletion(ChatCompletionRequest.builder().messages(messages).stop(stopReasons).maxTokens(maxTokens).topP(topP).model(model).build());
    }

    public ChatCompletionResult sendRequest(@NotNull List<ChatMessage> messages, @NotNull String model) {
        return openAiService.createChatCompletion(ChatCompletionRequest.builder().messages(messages).model(model).build());
    }

    public File uploadFile(@NotNull String purpose, @NotNull String path) {
        return openAiService.uploadFile(purpose, path);
    }

    public List<File> listFiles() {
        return openAiService.listFiles();
    }

    public File getFile(String fileId) {
        return openAiService.retrieveFile(fileId);
    }

    public DeleteResult deleteFile(String fileId) {
        return openAiService.deleteFile(fileId);
    }

    public FineTuningJob createFineTune(@NotNull String model, @NotNull String fileId, @NotNull Integer epochs, @NotNull String suffix) {
        return openAiService.createFineTuningJob(FineTuningJobRequest.builder().model(model).trainingFile(fileId)
                .hyperparameters(Hyperparameters.builder().nEpochs(epochs).build()).suffix(suffix).build());
    }

    public List<FineTuningJob> listFineTunes() {
        return openAiService.listFineTuningJobs();
    }

    public FineTuningJob getFineTune(String fineTuneId) {
        return openAiService.retrieveFineTuningJob(fineTuneId);
    }

    public FineTuningJob cancelFineTune(String fineTuneId) {
        return openAiService.cancelFineTuningJob(fineTuneId);
    }

    public DeleteResult deleteFineTune(String fineTuneId) {
        return openAiService.deleteFineTune(fineTuneId);
    }

    public ChatCompletionResult sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage("user", message);
        List<ChatMessage> messages = Collections.singletonList(chatMessage);
        return sendRequest(messages, EMBEDDING_MODEL);
    }
    public String getSimpleChatResponse(String message) {
        ChatMessage chatMessage = new ChatMessage("user", message);
        List<ChatMessage> messages = Collections.singletonList(chatMessage);
        ChatCompletionResult result = sendRequest(messages, EMBEDDING_MODEL);
        if (result != null && !result.getChoices().isEmpty() && result.getChoices().get(0).getMessage().getContent() != null) {
            return result.getChoices().get(0).getMessage().getContent();
        }
        return "No response received";
    }
}

