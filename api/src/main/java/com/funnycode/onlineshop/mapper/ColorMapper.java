package com.funnycode.onlineshop.mapper;

import com.funnycode.onlineshop.entity.Color;
import com.funnycode.onlineshop.dto.ColorDTOCreate;
import com.funnycode.onlineshop.dto.ColorDTOResponse;
import com.funnycode.onlineshop.dto.ColorDTOUpdate;

public class ColorMapper {
    public static Color toColor(ColorDTOCreate colorDTOCreate) {
        return Color.builder()
                .colorName(colorDTOCreate.getColorName())
                .build();
    }

    public static ColorDTOResponse toColorDTOResponse(Color color) {
        return ColorDTOResponse.builder()
                .id(color.getId())
                .colorName(color.getColorName())
                .build();
    }

    public static Color toColor(ColorDTOUpdate colorDTOUpdate) {
        return Color.builder()
                .colorName(colorDTOUpdate.getColorName())
                .build();
    }
}
