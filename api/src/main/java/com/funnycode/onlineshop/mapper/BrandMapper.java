package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.entity.Brand;
import com.funnycode.onlineshop.dto.BrandDTOCreate;
import com.funnycode.onlineshop.dto.BrandDTOResponse;
import com.funnycode.onlineshop.dto.BrandDTOUpdate;

public class BrandMapper {
    public static Brand toBrand(BrandDTOCreate brandDTOCreate) {
        return Brand.builder()
                .branchName(brandDTOCreate.getBranchName())
                .build();
    }

    public static BrandDTOResponse toBrandDTOResponse(Brand brand) {
        return BrandDTOResponse.builder()
                .id(brand.getId())
                .branchName(brand.getBranchName())
                .build();
    }

    public static Brand toBrand(BrandDTOUpdate brandDTOUpdate) {
        return Brand.builder()
                .branchName(brandDTOUpdate.getBranchName())
                .build();
    }
}
