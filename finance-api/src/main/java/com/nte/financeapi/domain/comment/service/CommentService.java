package com.nte.financeapi.domain.comment.service;

import com.nte.financeapi.domain.comment.dto.request.CreateCommentRequest;
import com.nte.financecore.domain.Comment;
import com.nte.financecore.domain.Research;
import com.nte.financecore.domain.User;
import com.nte.financecore.repository.CommentRepository;
import com.nte.financecore.repository.ResearchRepository;
import com.nte.financecore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ResearchRepository researchRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CreateCommentRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("Could not found user id : " + request.getUserId()));

        Research research = researchRepository.findById(request.getResearchId())
                .orElseThrow(() -> new NotFoundException("Could not found research id : " + request.getResearchId()));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdDate(request.getCreatedDateTime())
                .updatedDate(request.getUpdatedDateTime())
                .user(user)
                .research(research)
                .build();

        if(request.getParentId() != null){
            Comment parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new NotFoundException("Could not found content id : " + request.getParentId()));

            comment.setParent(parent);
        }

        commentRepository.save(comment);
    }
}
