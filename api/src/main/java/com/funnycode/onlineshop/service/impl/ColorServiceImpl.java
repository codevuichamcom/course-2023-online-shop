package com.funnycode.onlineshop.service.impl;

import com.funnycode.onlineshop.entity.Color;
import com.funnycode.onlineshop.dto.ColorDTOCreate;
import com.funnycode.onlineshop.dto.ColorDTOResponse;
import com.funnycode.onlineshop.dto.ColorDTOUpdate;
import com.funnycode.onlineshop.exception.OnlineShopException;
import com.funnycode.onlineshop.mapper.ColorMapper;
import com.funnycode.onlineshop.repository.ColorRepository;
import com.funnycode.onlineshop.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private ColorRepository colorRepository;

    @Override
    public ColorDTOResponse createColor(ColorDTOCreate ColorDTOCreate) {
        Color Color = colorRepository.save(ColorMapper.toColor(ColorDTOCreate));
        return ColorMapper.toColorDTOResponse(Color);
    }

    @Override
    public List<ColorDTOResponse> getAllColors() {
        return colorRepository.findAll().stream()
                .map(ColorMapper::toColorDTOResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ColorDTOResponse getColorById(int id) {
        Color Color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color does not exist"));
        return ColorMapper.toColorDTOResponse(Color);
    }

    @Override
    public ColorDTOResponse updateColor(int id, ColorDTOUpdate ColorDTOUpdate) {
        if (!colorRepository.existsById(id)) {
            throw OnlineShopException.notFoundException("Color does not exist");
        }
        Color Color = ColorMapper.toColor(ColorDTOUpdate);
        Color.setId(id);
        return ColorMapper.toColorDTOResponse(colorRepository.save(Color));
    }

    @Override
    public ColorDTOResponse deleteColor(int id) {
        Color Color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Color id does not exist"));
        colorRepository.delete(Color);
        return ColorMapper.toColorDTOResponse(Color);
    }
}
