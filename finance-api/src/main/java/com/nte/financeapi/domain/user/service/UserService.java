package com.nte.financeapi.domain.user.service;

import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financeapi.domain.user.dto.response.ReadUserResponse;
import com.nte.financecore.domain.User;
import com.nte.financecore.repository.CommentRepository;
import com.nte.financecore.repository.ResearchRepository;
import com.nte.financecore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<ReadUserResponse> readUserList(){

        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(user -> ReadUserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .createdDateTime(user.getCreatedDateTime())
                        .updatedDateTime(user.getUpdatedDateTime())
                        .ratingScore(user.getRating().getScore())
                        .build()
                ).toList();
    }


}
