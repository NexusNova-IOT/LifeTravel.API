package com.nexusnova.lifetravelapi.app.core.tours.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.Agency;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.model.Vehicle;
import com.nexusnova.lifetravelapi.app.reporting.domain.model.Review;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tour_packages")
public class TourPackage extends AuditModel {
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "visible", nullable = false)
    private Boolean visible = false;

    @Column(name = "rating")
    @Max(5)
    private Float rating;

    @Column(name = "latitude", columnDefinition = "decimal(13,10)")
    private BigDecimal latitude;

    @Column(name = "longitude", columnDefinition = "decimal(13,10)")
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    @JsonIgnore
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    @JsonIgnore
    private Agency agency;

    @ManyToMany
    @JoinTable(name = "tour_package_activities",
            joinColumns = @JoinColumn(name = "tour_package_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> activities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourPackage", cascade = CascadeType.ALL)
    private List<Destination> destinations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourPackage")
    private List<Schedule> schedules;

    @ManyToMany
    @JoinTable(name = "assigned_vehicles",
            joinColumns = @JoinColumn(name = "tour_package_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tourPackage", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
