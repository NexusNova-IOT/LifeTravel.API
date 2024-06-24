package com.nexusnova.lifetravelapi.app.shared.util;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.*;
import com.nexusnova.lifetravelapi.app.core.tours.domain.repositories.*;
import com.nexusnova.lifetravelapi.app.iam.identity.domain.model.*;
import com.nexusnova.lifetravelapi.app.iam.identity.domain.repositories.*;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.model.*;
import com.nexusnova.lifetravelapi.app.iam.profile.domain.repositories.*;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.model.Vehicle;
import com.nexusnova.lifetravelapi.app.core.transportation.domain.repositories.VehicleRepository;
import com.nexusnova.lifetravelapi.app.shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.nexusnova.lifetravelapi.app.shared.util.CoreConstants.AGENCY_ROLE_ID;
import static com.nexusnova.lifetravelapi.app.shared.util.CoreConstants.TOURIST_ROLE_ID;

@Component
public class ValidationUtil {

    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final TouristRepository touristRepository;
    private final ActivityRepository activityRepository;
    private final DepartmentRepository departmentRepository;
    private final DestinationRepository destinationRepository;
    private final RegionRepository regionRepository;
    private final ScheduleRepository scheduleRepository;
    private final TourPackageRepository tourPackageRepository;
    private final RoleRepository roleRepository;
    private final VehicleRepository vehicleRepository;
    public ValidationUtil(UserRepository userRepository,
                          AgencyRepository agencyRepository,
                          TouristRepository touristRepository,
                          ActivityRepository activityRepository,
                          DepartmentRepository departmentRepository,
                          DestinationRepository destinationRepository,
                          RegionRepository regionRepository,
                          ScheduleRepository scheduleRepository,
                          TourPackageRepository tourPackageRepository,
                          VehicleRepository vehicleRepository,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.agencyRepository = agencyRepository;
        this.touristRepository = touristRepository;
        this.activityRepository = activityRepository;
        this.departmentRepository = departmentRepository;
        this.destinationRepository = destinationRepository;
        this.regionRepository = regionRepository;
        this.scheduleRepository = scheduleRepository;
        this.tourPackageRepository = tourPackageRepository;
        this.vehicleRepository = vehicleRepository;
        this.roleRepository = roleRepository;
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public Role getTouristRole() {
        return roleRepository.findById(TOURIST_ROLE_ID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for tourist"));
    }

    public Role getAgencyRole() {
        return roleRepository.findById(AGENCY_ROLE_ID)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for agency"));
    }
    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    public Agency findAgencyById(Long id) {
        return agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));
    }

    public Tourist findTouristById(Long id) {
        return touristRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with id: " + id));
    }

    public Agency findAgencyByUserId(String id) {
        return agencyRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with user id: " + id));
    }

    public Tourist findTouristByUserId(String id) {
        return touristRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tourist not found with user id: " + id));
    }

    public Activity findActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
    }

    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Department findDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with name: " + name));
    }

    public Destination findDestinationById(Long id) {
        return destinationRepository.findById(id).orElse(new Destination());
    }
    public Region findRegionById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found with id: " + id));
    }

    public Schedule findTourExperienceById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TourExperience not found with id: " + id));
    }

    public TourPackage findTourPackageById(Long id) {
        return tourPackageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TourPackage not found with id: " + id));
    }

    public List<TourPackage> findTourPackagesByIds(List<Long> ids) {
        return tourPackageRepository.findByIdIn(ids);
    }

}
