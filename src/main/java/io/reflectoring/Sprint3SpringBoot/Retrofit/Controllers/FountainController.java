package io.reflectoring.Sprint3SpringBoot.Retrofit.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.FountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/fountains")
@CrossOrigin(origins = "http://localhost:4200")
public class FountainController {

    private final FountainService fountainService;

    @Autowired
    public FountainController(FountainService fountainService) {
        this.fountainService = fountainService;
    }

    @GetMapping
    public ResponseEntity<List<FountainDto>> getAllFountains() {
        try {
            System.out.println("FONTANARIOS");
            List<FountainDto> fountains = fountainService.getAllFountains();
            return fountains.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok()
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .body(fountains);
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FountainDto> getFountainById(@PathVariable("id") int fountainId) {
        if (fountainId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            FountainDto fountain = fountainService.getFountainById(fountainId);
            return fountain != null ? ResponseEntity.ok(fountain) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<FountainDto> createFountain(@RequestBody FountainDto fountain) {
        try {
            FountainDto fountainCreated = fountainService.createFountain(fountain);
            return new ResponseEntity<>(fountainCreated, HttpStatus.CREATED);
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FountainDto> updateFountain(@RequestBody FountainDto fountain, @PathVariable("id") int fountainId) {
        if (fountainId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            FountainDto updatedFountain = fountainService.updateFountain(fountainId, fountain);
            return new ResponseEntity<>(updatedFountain, HttpStatus.OK);
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFountain(@PathVariable("id") int fountainId) {
        if (fountainId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            Boolean deleted = fountainService.deleteFountain(fountainId);
            return deleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
