package com.Quora.Quora.Backedn.Api.repository;

import com.Quora.Quora.Backedn.Api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findByAnswerId(UUID answerId);
    List<Comment> findByParentCommentId(UUID parentCommentId);
}
