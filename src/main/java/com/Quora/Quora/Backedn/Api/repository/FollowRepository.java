package com.Quora.Quora.Backedn.Api.repository;

import com.Quora.Quora.Backedn.Api.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {}
