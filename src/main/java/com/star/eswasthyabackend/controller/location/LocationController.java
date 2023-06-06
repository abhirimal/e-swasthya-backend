package com.star.eswasthyabackend.controller.location;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/province-list")
    public ResponseEntity<?> viewAllProvinceList(){

        return ResponseEntity.ok(new ApiResponse(true,
                "Provinces list fetched successfully.",
                locationService.viewAllProvinceList()));
    }

    @GetMapping("/district-list-by-province/{provinceId}")
    public ResponseEntity<?> viewAllDistrict(@PathVariable Integer provinceId){

        return ResponseEntity.ok(new ApiResponse(true,
                "Districts list fetched successfuolly.",
                locationService.viewAllDistrictByProvince(provinceId)));
    }

    @GetMapping("municipality-list-by-district/{districtId}")
    public ResponseEntity<?> viewAllMunicipalityByDistrict(@PathVariable Integer districtId){

        return ResponseEntity.ok(new ApiResponse(true,
                "Municipalities list fetched successfully.",
                locationService.viewAllMunicipalityByDistrictId(districtId)));
    }

}
