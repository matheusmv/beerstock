package com.github.matheusmv.beerstock.service;

import com.github.matheusmv.beerstock.dto.BeerDTO;
import com.github.matheusmv.beerstock.entity.Beer;
import com.github.matheusmv.beerstock.exception.BeerAlreadyRegisteredException;
import com.github.matheusmv.beerstock.exception.BeerNotFoundException;
import com.github.matheusmv.beerstock.exception.BeerStockExceededException;
import com.github.matheusmv.beerstock.mapper.BeerMapper;
import com.github.matheusmv.beerstock.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO.getName());

        var beer = beerMapper.toModel(beerDTO);
        var savedBeer = beerRepository.save(beer);

        return beerMapper.toDTO(savedBeer);
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        return beerRepository.findByName(name)
                .map(beerMapper::toDTO)
                .orElseThrow(() -> new BeerNotFoundException(name));
    }

    public List<BeerDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIfExists(id);

        beerRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
        var beer = beerRepository.findByName(name);

        if (beer.isPresent()) {
            throw new BeerAlreadyRegisteredException(name);
        }
    }

    private Beer verifyIfExists(Long id) throws BeerNotFoundException {
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

    public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        var beerToIncrementStock = verifyIfExists(id);
        var quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();

        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);

            var incrementedBeerStock = beerRepository.save(beerToIncrementStock);

            return beerMapper.toDTO(incrementedBeerStock);
        }

        throw new BeerStockExceededException(id, quantityToIncrement);
    }
}
