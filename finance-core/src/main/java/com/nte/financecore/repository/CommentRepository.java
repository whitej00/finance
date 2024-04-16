package com.nte.financecore.repository;

import com.nte.financecore.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByResearchId(Long id, Sort sort);
}
