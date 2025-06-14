package com.Quora.Quora.Backedn.Api.dto;

import java.util.UUID;

public class LikeRequest {
    private UUID userId;

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
}
