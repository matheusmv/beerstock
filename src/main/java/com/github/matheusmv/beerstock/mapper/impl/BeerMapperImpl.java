package com.github.matheusmv.beerstock.mapper.impl;

import com.github.matheusmv.beerstock.dto.BeerDTO;
import com.github.matheusmv.beerstock.entity.Beer;
import com.github.matheusmv.beerstock.mapper.BeerMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BeerMapperImpl implements BeerMapper {

    @Override
    public Beer toModel(BeerDTO beerDTO) {
        if (Objects.isNull(beerDTO)) {
            return null;
        }

        Beer beer = new Beer();

        beer.setId(beerDTO.getId());
        beer.setName(beerDTO.getName());
        beer.setBrand(beerDTO.getBrand());
        beer.setMax(beerDTO.getMax());
        beer.setQuantity(beerDTO.getQuantity());
        beer.setType(beerDTO.getType());

        return beer;
    }

    @Override
    public BeerDTO toDTO(Beer beer) {
        if (Objects.isNull(beer)) {
            return null;
        }

        return BeerDTO.builder()
                .id(beer.getId())
                .name(beer.getName())
                .brand(beer.getBrand())
                .max(beer.getMax())
                .quantity(beer.getQuantity())
                .type(beer.getType())
                .build();
    }
}
