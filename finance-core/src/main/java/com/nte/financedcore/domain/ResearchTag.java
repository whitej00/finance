package com.nte.financedcore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResearchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "research_id")
    private Research research;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void setResearch(Research research){
        this.research = research;
    }

    @Builder
    public ResearchTag(Tag tag){
        this.tag = tag;
        tag.addResearchTag(this);
    }
}
