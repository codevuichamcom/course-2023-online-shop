package com.funnycode.onlineshop.service;

import com.funnycode.onlineshop.dto.BrandDTOCreate;
import com.funnycode.onlineshop.dto.BrandDTOResponse;
import com.funnycode.onlineshop.dto.BrandDTOUpdate;

import java.util.List;

public interface BrandService {

    BrandDTOResponse createBrand(BrandDTOCreate brandDTOCreate);

    List<BrandDTOResponse> getAllBrands();

    BrandDTOResponse getBrandById(int id);

    BrandDTOResponse updateBrand(int id, BrandDTOUpdate brandDTOUpdate);

    BrandDTOResponse deleteBrand(int id);
}
