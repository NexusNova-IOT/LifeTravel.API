package com.nexusnova.lifetravelapi.app.core.tours.domain.model;

import com.nexusnova.lifetravelapi.app.assets.domain.model.WeatherSensor;
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
@Table(name = "destinations")
public class Destination extends AuditModel {

    @Column(name = "latitude", columnDefinition = "decimal(13,10)")
    private BigDecimal latitude;

    @Column(name = "longitude", columnDefinition = "decimal(13,10)")
    private BigDecimal longitude;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    private TourPackage tourPackage;

    @OneToOne(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private WeatherSensor weatherSensor;

}
