package com.nte.financeapi.domain.comment.service;

import com.nte.financeapi.domain.comment.dto.request.CreateCommentRequest;
import com.nte.financeapi.domain.comment.dto.request.UpdateCommentRequest;
import com.nte.financeapi.domain.comment.dto.response.ReadCommentResponse;
import com.nte.financecore.domain.Comment;
import com.nte.financecore.domain.Research;
import com.nte.financecore.domain.User;
import com.nte.financecore.repository.CommentRepository;
import com.nte.financecore.repository.ResearchRepository;
import com.nte.financecore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .createdDateTime(request.getCreatedDateTime())
                .updatedDateTime(request.getUpdatedDateTime())
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

    public List<ReadCommentResponse> readCommentListByResearch(Long researchId){

        List<Comment> commentList = commentRepository.findAllByResearchId(
                researchId,
                Sort.by(
                        Sort.Order.asc("id").nullsFirst(),
                        Sort.Order.asc("createdDateTime")
                ));

        Map<Long, ReadCommentResponse> dtoHashMap = new HashMap<>();
        List<ReadCommentResponse> readCommentResponseList = new ArrayList<>();

        commentList.forEach(comment -> {
            ReadCommentResponse dto = ReadCommentResponse.builder()
                    .id(comment.getId())
                    .userId(comment.getUser().getId())
                    .username(comment.getUser().getUsername())
                    .content(comment.getContent())
                    .createdDateTime(comment.getCreatedDateTime())
                    .updatedDateTime(comment.getUpdatedDateTime())
                    .build();

            dtoHashMap.put(comment.getId(), dto);
            if(comment.getParent() == null){
                readCommentResponseList.add(dto);
            }
            else{
                dtoHashMap.get(comment.getParent().getId()).getChildList().add(dto);
            }
        });

        return readCommentResponseList;
    }

    public List<ReadCommentResponse> readCommentListByUser(Long userId){

        List<Comment> commentList = commentRepository.findAllByUserId(
                userId,
                Sort.by(
                        Sort.Order.asc("id").nullsFirst(),
                        Sort.Order.asc("createdDateTime")
                ));

        Map<Long, ReadCommentResponse> dtoHashMap = new HashMap<>();
        List<ReadCommentResponse> readCommentResponseList = new ArrayList<>();

        commentList.forEach(comment -> {
            ReadCommentResponse dto = ReadCommentResponse.builder()
                    .id(comment.getId())
                    .userId(comment.getUser().getId())
                    .username(comment.getUser().getUsername())
                    .content(comment.getContent())
                    .createdDateTime(comment.getCreatedDateTime())
                    .updatedDateTime(comment.getUpdatedDateTime())
                    .build();

            dtoHashMap.put(comment.getId(), dto);
            if(comment.getParent() == null){
                readCommentResponseList.add(dto);
            }
            else{
                dtoHashMap.get(comment.getParent().getId()).getChildList().add(dto);
            }
        });

        return readCommentResponseList;
    }

    @Transactional
    public void deleteCommentById(@RequestParam Long id){

        commentRepository.deleteById(id);
    }

    @Transactional
    public void update(UpdateCommentRequest request){

        Comment comment = commentRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("comment doesn't exist")
        );

        comment.update(request.getContent());
    }
}
