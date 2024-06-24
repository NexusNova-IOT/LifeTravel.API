package com.nexusnova.lifetravelapi.app.core.tours.api.rest;

import com.nexusnova.lifetravelapi.app.core.tours.domain.services.OpenAIClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat-completions")
@Tag(name = "AI completitions")
@CrossOrigin
public class ChatCompletionController {

    private final OpenAIClientService openAIClientService;

    public ChatCompletionController(OpenAIClientService openAIClientService) {
        this.openAIClientService = openAIClientService;
    }

    @PostMapping
    public ResponseEntity<String> getChatResponse(@RequestBody String message) {
        String basePrompt = """
            Teniendo en cuenta las siguientes regiones: costa(1), sierra(2) y selva(3), selecciona la mejor región según el mensaje del usuario: '%s'
            Por favor, proporciona una respuesta concisa con el formato:
            {
                "message": "response",
                "region": "0"
            }
            """;
        String formattedPrompt = String.format(basePrompt, message);
        String result = openAIClientService.getSimpleChatResponse(formattedPrompt);
        return ResponseEntity.ok(result);
    }
}