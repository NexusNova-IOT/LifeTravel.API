package com.nexusnova.lifetravelapi.app.iam.profile.domain.model;

import com.nexusnova.lifetravelapi.app.iam.identity.domain.model.User;
import com.nexusnova.lifetravelapi.app.assets.domain.model.TrackingWearable;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tourists")
public class Tourist extends AuditModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "emergency_phone_number")
    private String emergencyPhoneNumber;

    @OneToOne(mappedBy = "tourist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TrackingWearable trackingWereables;
}
