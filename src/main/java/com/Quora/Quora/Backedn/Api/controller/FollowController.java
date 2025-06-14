package com.Quora.Quora.Backedn.Api.controller;

import com.Quora.Quora.Backedn.Api.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<Map<String, String>> followUser(
            @PathVariable UUID userId,
            @PathVariable UUID targetUserId) {
        followService.followUser(userId, targetUserId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "User followed successfully"));
    }
}
