package com.Quora.Quora.Backedn.Api.service;

import com.Quora.Quora.Backedn.Api.exceptions.ResourceNotFoundException;
import com.Quora.Quora.Backedn.Api.model.Follow;
import com.Quora.Quora.Backedn.Api.model.User;
import com.Quora.Quora.Backedn.Api.repository.FollowRepository;
import com.Quora.Quora.Backedn.Api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void followUser(UUID userId, UUID targetUserId) {
        User follower = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        User following = userRepository.findById(targetUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Target user not found"));
        Follow follow = Follow.builder().follower(follower).following(following).build();
        followRepository.save(follow);
    }
}
