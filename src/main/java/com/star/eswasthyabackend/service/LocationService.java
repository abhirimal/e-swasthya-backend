package com.star.eswasthyabackend.service;

import java.util.List;
import java.util.Map;

public interface LocationService {
    List<Map<String, Object>> viewAllProvinceList();

    List<Map<String, Object>> viewAllDistrictByProvince(Integer provinceId);

    List<Map<String, Object>> viewAllMunicipalityByDistrictId(Integer districtId);

    List<Map<String, Object>> getLocationInJson();
}
