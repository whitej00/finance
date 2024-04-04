package com.nte.financedcore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Research {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "research")
    List<ResearchTag> researchTagList = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "research")
    private EvaluationStatus evaluationStatus;

    private String title;
    private String content;

    private LocalDateTime createdDate;

    private LocalDate targetRangeStart;
    private LocalDate targetRangeEnd;

    private Long targetPrice;

    public void addResearchTag(ResearchTag researchTag){
        this.researchTagList.add(researchTag);
        researchTag.setResearch(this);
    }
    public void setUser(User user){
        this.user = user;
        user.addResearch(this);
    }

    public void setEvaluationStatus(EvaluationStatus evaluationStatus){
        this.evaluationStatus = evaluationStatus;
        evaluationStatus.setResearch(this);
    }


    @Builder
    public Research(List<ResearchTag> researchTagList, User user, EvaluationStatus evaluationStatus, String title, String content, LocalDateTime createdDate, LocalDate targetRangeStart, LocalDate targetRangeEnd, Long targetPrice) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.targetRangeStart = targetRangeStart;
        this.targetRangeEnd = targetRangeEnd;
        this.targetPrice = targetPrice;

        for(ResearchTag researchTag : researchTagList){
            this.addResearchTag(researchTag);
        }

        this.setUser(user);

        this.setEvaluationStatus(evaluationStatus);
    }
}
