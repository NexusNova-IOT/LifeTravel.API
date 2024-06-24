package com.nexusnova.lifetravelapi.app.reporting.domain.model;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.TourPackage;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Tourist;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reviews")
public class Review extends AuditModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourist_id")
    @NotNull
    private Tourist tourist;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    @NotNull
    private TourPackage tourPackage;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review_text")
    private String reviewText;
}
