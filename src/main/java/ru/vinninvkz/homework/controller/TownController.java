package ru.vinninvkz.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vinninvkz.homework.dto.TownDto;
import ru.vinninvkz.homework.service.TownService;

@RestController
@RequestMapping("/api/v1/town")
public class TownController {
    private final TownService townService;

    @Autowired
    public TownController(TownService townService) {
        this.townService = townService;
    }

    @PostMapping
    public ResponseEntity<TownDto> addTown(@RequestBody TownDto townDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(townService.addTown(townDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TownDto> updateTown(@PathVariable Long id,
                                              @RequestParam long population,
                                              @RequestParam boolean hasMetro) {
        return ResponseEntity.ok(townService.updateTown(id, population, hasMetro));
    }

}
