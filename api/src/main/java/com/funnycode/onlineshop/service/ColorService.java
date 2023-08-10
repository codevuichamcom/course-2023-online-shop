package com.funnycode.onlineshop.service;

import com.funnycode.onlineshop.dto.ColorDTOCreate;
import com.funnycode.onlineshop.dto.ColorDTOResponse;
import com.funnycode.onlineshop.dto.ColorDTOUpdate;

import java.util.List;

public interface ColorService {
    ColorDTOResponse createColor(ColorDTOCreate colorDTOCreate);

    List<ColorDTOResponse> getAllColors();

    ColorDTOResponse getColorById(int id);

    ColorDTOResponse updateColor(int id, ColorDTOUpdate colorDTOUpdate);

    ColorDTOResponse deleteColor(int id);
}
