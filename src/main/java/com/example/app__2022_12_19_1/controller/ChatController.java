package com.example.app__2022_12_19_1.controller;

import com.example.app__2022_12_19_1.domain.ChatMessage;
import com.example.app__2022_12_19_1.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
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

    @AllArgsConstructor
    @Getter
    public static class MessagesResponse {
        private final List<ChatMessage> messages;
    }

    @AllArgsConstructor
    @Getter
    public static class MessagesRequest {
        private int fromId;
    }

    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages(MessagesRequest request) {
        MessagesResponse fromChatMessages = new MessagesResponse(chatMessages);

        if(request.getFromId() != 0) {
            int index = IntStream.range(0, chatMessages.size()).filter(i -> chatMessages.get(i).getId() == request.getFromId()).findFirst().orElse(-1);

            if(index != -1) fromChatMessages = new MessagesResponse(chatMessages.subList(index, chatMessages.size()));
        }

        return new RsData("S-1", "메세지가 작성되었습니다", fromChatMessages.getMessages());
    }
}
