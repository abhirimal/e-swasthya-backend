package com.star.eswasthyabackend.service;

import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.LocationRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{

    private final DistrictRepository districtRepository;

    private final MunicipalityRepository municipalityRepository;
    @Override
    public List<Map<String, Object>> viewAllProvinceList() {
        return districtRepository.listAllProvince();
    }

    @Override
    public List<Map<String, Object>> viewAllDistrictByProvince(Integer provinceId) {
        return districtRepository.listDistrictByProvinceNo(provinceId);
    }

    @Override
    public List<Map<String, Object>> viewAllMunicipalityByDistrictId(Integer districtId) {
        return municipalityRepository.listMunicipalityByDistrictId(districtId);
    }


}
