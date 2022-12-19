package com.example.app__2022_12_19_1.controller;

import com.example.app__2022_12_19_1.domain.ChatMessage;
import com.example.app__2022_12_19_1.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse {
        private final long id;
    }

    @AllArgsConstructor
    @Getter
    public static class WriteMessageRequest {
        private String authorName;
        private String content;
    }

    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatMessage> writeMessage(WriteMessageRequest request) {
        ChatMessage chatMessage = new ChatMessage(request.getAuthorName(), request.getContent());

        chatMessages.add(chatMessage);

        return new RsData("S-1", "메세지가 작성되었습니다", new WriteMessageResponse(chatMessage.getId()));
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> messages(@RequestParam(defaultValue = "0") int fromId) {

        List<ChatMessage> fromChatMessages= null;

        if(fromId == 0) fromChatMessages = chatMessages;
        else fromChatMessages = chatMessages.stream().skip(fromId).collect(Collectors.toList());

        return new RsData("S-1", "메세지가 작성되었습니다", fromChatMessages);
    }
}
