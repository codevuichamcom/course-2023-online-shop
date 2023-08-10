package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.dto.BrandDTOCreate;
import com.funnycode.onlineshop.dto.BrandDTOResponse;
import com.funnycode.onlineshop.dto.BrandDTOUpdate;
import com.funnycode.onlineshop.entity.Brand;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.mapper.BrandMapper;
import com.funnycode.onlineshop.repository.BrandRepository;
import com.funnycode.onlineshop.service.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Override
    public BrandDTOResponse createBrand(BrandDTOCreate brandDTOCreate) {
        Brand brand = brandRepository.save(BrandMapper.toBrand(brandDTOCreate));
        return BrandMapper.toBrandDTOResponse(brand);
    }

    @Override
    public List<BrandDTOResponse> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(BrandMapper::toBrandDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDTOResponse getBrandById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand does not exist"));
        return BrandMapper.toBrandDTOResponse(brand);
    }

    @Override
    public BrandDTOResponse updateBrand(int id, BrandDTOUpdate brandDTOUpdate) {
        if (!brandRepository.existsById(id)) {
            throw OnlineShopException.notFoundException("Brand does not exist");
        }
        Brand brand = BrandMapper.toBrand(brandDTOUpdate);
        brand.setId(id);
        return BrandMapper.toBrandDTOResponse(brandRepository.save(brand));
    }

    @Override
    public BrandDTOResponse deleteBrand(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand id does not exist"));
        brandRepository.delete(brand);
        return BrandMapper.toBrandDTOResponse(brand);
    }
}
