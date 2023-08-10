package com.funnycode.onlineshop.controller;

import com.funnycode.onlineshop.dto.ColorDTOCreate;
import com.funnycode.onlineshop.dto.ColorDTOResponse;
import com.funnycode.onlineshop.dto.ColorDTOUpdate;
import com.funnycode.onlineshop.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.funnycode.onlineshop.util.Constant.API_VERSION;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(API_VERSION + "/colors")
@CrossOrigin
public class ColorController {

    private ColorService colorService;

    @PostMapping
    public ColorDTOResponse createColor(@RequestBody ColorDTOCreate ColorDTOCreate) {
        return colorService.createColor(ColorDTOCreate);
    }

    @GetMapping
    public List<ColorDTOResponse> getAllColors() {
        return colorService.getAllColors();
    }

    @GetMapping("/{id}")
    public ColorDTOResponse getColorById(@PathVariable int id) {
        return colorService.getColorById(id);
    }

    @PutMapping("/{id}")
    public ColorDTOResponse updateColor(@PathVariable int id, @RequestBody ColorDTOUpdate ColorDTOUpdate) {
        return colorService.updateColor(id, ColorDTOUpdate);
    }

    @DeleteMapping("/{id}")
    public ColorDTOResponse deleteColor(@PathVariable int id) {
        return colorService.deleteColor(id);
    }
}
