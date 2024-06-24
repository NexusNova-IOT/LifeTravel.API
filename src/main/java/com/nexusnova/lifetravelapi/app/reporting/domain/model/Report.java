package com.nexusnova.lifetravelapi.app.reporting.domain.model;

import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Agency;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reports")
public class Report extends AuditModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agency_id")
    @NotNull
    private Agency agency;

    @ManyToMany
    @JoinTable(
            name = "report_reviews",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private Set<Review> reviews;

    @Column(name = "content")
    private String content;

    @Column(name = "ai_recommendation")
    private String aiRecommendation;
}
