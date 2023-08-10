package com.funnycode.onlineshop.controller;

import com.funnycode.onlineshop.dto.BrandDTOCreate;
import com.funnycode.onlineshop.dto.BrandDTOResponse;
import com.funnycode.onlineshop.dto.BrandDTOUpdate;
import com.funnycode.onlineshop.service.BrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.funnycode.onlineshop.util.Constant.API_VERSION;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(API_VERSION + "/brands")
@CrossOrigin
public class BrandController {

    private BrandService brandService;

    @PostMapping
    public BrandDTOResponse createBrand(@RequestBody BrandDTOCreate brandDTOCreate) {
        return brandService.createBrand(brandDTOCreate);
    }

    @GetMapping
    public List<BrandDTOResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public BrandDTOResponse getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id);
    }

    @PutMapping("/{id}")
    public BrandDTOResponse updateBrand(@PathVariable int id, @RequestBody BrandDTOUpdate brandDTOUpdate) {
        return brandService.updateBrand(id, brandDTOUpdate);
    }

    @DeleteMapping("/{id}")
    public BrandDTOResponse deleteBrand(@PathVariable int id) {
        return brandService.deleteBrand(id);
    }
}
