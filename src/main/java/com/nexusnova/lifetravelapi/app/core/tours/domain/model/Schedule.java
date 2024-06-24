package com.nexusnova.lifetravelapi.app.core.tours.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexusnova.lifetravelapi.app.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "schedule")
public class Schedule extends AuditModel {

    @Column(name = "day")
    private String day;

    @Column(name = "start_hour")
    private String startHour;

    @Column(name = "start_minute")
    private String startMinute;

    @Column(name = "start_day_time")
    private String startDayTime;

    @Column(name = "end_hour")
    private String endHour;

    @Column(name = "end_minute")
    private String endMinute;

    @Column(name = "end_day_time")
    private String endDayTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    @JsonIgnore
    private TourPackage tourPackage;

}
