package com.github.matheusmv.beerstock.controller;

import com.github.matheusmv.beerstock.dto.BeerDTO;
import com.github.matheusmv.beerstock.dto.QuantityDTO;
import com.github.matheusmv.beerstock.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

    private final BeerService beerService;

    @PostMapping
    public ResponseEntity<BeerDTO> createBeer(@RequestBody @Valid BeerDTO beerDTO) {

        var newBeer = beerService.createBeer(beerDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBeer.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newBeer);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BeerDTO> findByName(@PathVariable String name) {
        var beer = beerService.findByName(name);

        return ResponseEntity.ok().body(beer);
    }

    @GetMapping
    public ResponseEntity<List<BeerDTO>> listBeers() {
        var listOfBeers = beerService.listAll();

        return ResponseEntity.ok().body(listOfBeers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        beerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/increment")
    public ResponseEntity<BeerDTO> increment(@PathVariable Long id,
                                             @RequestBody @Valid QuantityDTO quantityDTO) {
        var beer = beerService.increment(id, quantityDTO.getQuantity());

        return ResponseEntity.ok().body(beer);
    }

    @PatchMapping("/{id}/decrement")
    public ResponseEntity<BeerDTO> decrement(@PathVariable Long id,
                                             @RequestBody @Valid QuantityDTO quantityDTO) {
        var beer = beerService.decrement(id, quantityDTO.getQuantity());

        return ResponseEntity.ok().body(beer);
    }
}
