package com.Quora.Quora.Backedn.Api.repository;

import com.Quora.Quora.Backedn.Api.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {}
