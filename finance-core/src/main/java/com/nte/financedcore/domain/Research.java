package com.nte.financedcore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Builder
    public Research(List<ResearchTag> researchTagList, String title, String content, LocalDateTime createdDate, LocalDate targetRangeStart, LocalDate targetRangeEnd, Long targetPrice) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.targetRangeStart = targetRangeStart;
        this.targetRangeEnd = targetRangeEnd;
        this.targetPrice = targetPrice;

        for(ResearchTag researchTag : researchTagList){
            this.addResearchTag(researchTag);
        }
    }
}
