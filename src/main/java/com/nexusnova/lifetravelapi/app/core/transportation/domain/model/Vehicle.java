package com.nexusnova.lifetravelapi.app.core.transportation.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Agency;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.enums.VehicleStatus;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "vehicles")

public class Vehicle extends AuditModel {
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "plate")
    private String plate;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "driver_name")
    private String driverName;
    @Column(name="weight")
    private BigDecimal weight;
    @Column(name="img_url")
    private String img;
    @Column(name="status")
    private VehicleStatus status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    @JsonIgnore
    private Agency agency;
}