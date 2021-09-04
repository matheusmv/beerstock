package com.github.matheusmv.beerstock.service;

import com.github.matheusmv.beerstock.builder.BeerDTOBuilder;
import com.github.matheusmv.beerstock.exception.BeerAlreadyRegisteredException;
import com.github.matheusmv.beerstock.exception.BeerNotFoundException;
import com.github.matheusmv.beerstock.mapper.BeerMapper;
import com.github.matheusmv.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    private static final long VALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;

    @Test
    void whenBeerInformedThenItShouldBeCreated() {
        // given
        var expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        var expectedSavedBeer = beerMapper.toModel(expectedBeerDTO);

        // when
        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer);

        // then
        var createdBeerDTO = beerService.createBeer(expectedBeerDTO);

        assertAll("beer creation test",
                () -> assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId()))),
                () -> assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName()))),
                () -> assertThat(createdBeerDTO.getQuantity(), is(equalTo(expectedBeerDTO.getQuantity()))),
                () -> assertThat(createdBeerDTO.getQuantity(), is(greaterThan(2)))
        );
    }

    @Test
    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrown() {
        // given
        var expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        var duplicatedBeer = beerMapper.toModel(expectedBeerDTO);

        // when
        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

        // then
        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));
    }

    @Test
    void whenValidBeerIsGivenThenReturnABeer() {
        // given
        var expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        var expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

        // when
        when(beerRepository.findByName(expectedFoundBeer.getName())).thenReturn(Optional.of(expectedFoundBeer));

        // then
        var foundBeerDTO = beerService.findByName(expectedFoundBeerDTO.getName());

        assertThat(foundBeerDTO, is(equalTo(expectedFoundBeerDTO)));
    }

    @Test
    void whenNoRegisteredBeerNameIsGivenThenThrowAnException() {
        // given
        var expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

        // when
        when(beerRepository.findByName(expectedFoundBeerDTO.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(BeerNotFoundException.class, () -> beerService.findByName(expectedFoundBeerDTO.getName()));
    }
}
