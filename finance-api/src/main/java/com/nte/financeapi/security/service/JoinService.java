package com.nte.financeapi.security.service;

import com.nte.financeapi.security.dto.JoinUserRequest;
import com.nte.financecore.domain.Rating;
import com.nte.financecore.domain.User;
import com.nte.financecore.repository.RatingRepository;
import com.nte.financecore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinProcess(JoinUserRequest request){

        String username = request.getUsername();

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist){

            return;
        }

        Rating rating = Rating.builder().build();

        ratingRepository.save(rating);

        User user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .createdDateTime(request.getCreatedDateTime())
                .updatedDateTime(request.getUpdatedDateTime())
                .role("ROLE_ADMIN")
                .rating(rating)
                .build();

        userRepository.save(user);
    }
}
