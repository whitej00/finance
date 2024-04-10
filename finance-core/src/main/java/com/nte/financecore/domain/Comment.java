package com.nte.financecore.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> childList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "research_id")
    private Research research;

    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public void setParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }
    private void addChild(Comment child){
        this.childList.add(child);
    }

    private void setUser(User user) {
        this.user = user;
        user.addComment(this);
    }

    private void setResearch(Research research){
        this.research = research;
        research.addComment(this);
    }

    @Builder
    public Comment(User user, Research research, String content, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;

        this.setUser(user);
        this.setResearch(research);
    }
}
