package com.nexusnova.lifetravelapi.app.assets.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexusnova.lifetravelapi.app.iam.identity.domain.model.User;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Tourist;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tracking_wereables")
public class TrackingWearable extends AuditModel {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tourist_id")
    private Tourist tourist;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tourist_user_id")
    @JsonIgnore
    private User touristUser;

    @Column(name = "latitude", columnDefinition = "decimal(13,10)")
    @Null
    private BigDecimal latitude;

    @Column(name = "longitude", columnDefinition = "decimal(13,10)")
    @Null
    private BigDecimal longitude;

    @Formula("concat(serie,'-', number)")
    private String serieNumber;

    @Column(name = "serie")
    private String serie;

    @Column(name = "number")
    private String number;
}
