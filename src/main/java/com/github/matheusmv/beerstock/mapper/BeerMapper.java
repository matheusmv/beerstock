package com.github.matheusmv.beerstock.mapper;

import com.github.matheusmv.beerstock.dto.BeerDTO;
import com.github.matheusmv.beerstock.entity.Beer;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
