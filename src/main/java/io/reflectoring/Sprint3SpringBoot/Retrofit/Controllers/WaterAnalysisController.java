package io.reflectoring.Sprint3SpringBoot.Retrofit.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.UserFavoritesFountainsDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserFavoritesWaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.WaterAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-analysis")
public class WaterAnalysisController {

    private final WaterAnalysisService waterAnalysisService;

    @Autowired
    public WaterAnalysisController(WaterAnalysisService waterAnalysisService) {
        this.waterAnalysisService = waterAnalysisService;
    }

    @GetMapping
    public ResponseEntity<List<WaterAnalysisDto>> getAllWaterAnalysis() {
        try{
            List<WaterAnalysisDto> waterAnalysis = waterAnalysisService.getAllWaterAnalyses();
            return waterAnalysis.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(waterAnalysis, HttpStatus.OK);
        }catch (RetrofitException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterAnalysisDto> getWaterAnalysisById(@PathVariable("id") int id) {
        if(id <= 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }try{
            WaterAnalysisDto waterAnalysis = waterAnalysisService.getWaterAnalysisById(id);
            return new ResponseEntity<>(waterAnalysis, HttpStatus.OK);
        }catch (RetrofitException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterAnalysisDto> updateWaterAnalysisById(@RequestBody WaterAnalysisDto waterAnalysis, @PathVariable("id") int id) {
        if(id <= 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }try{
            WaterAnalysisDto updatedWaterAnalysis = waterAnalysisService.updateWaterAnalysis(id, waterAnalysis);
            return new ResponseEntity<>(updatedWaterAnalysis, HttpStatus.OK);
        }catch (RetrofitException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<WaterAnalysisDto> createWaterAnalysis(@RequestBody WaterAnalysisDto waterAnalysis) {
        try{
            WaterAnalysisDto newWaterAnalysis = waterAnalysisService.createWaterAnalysis(waterAnalysis);
            return new ResponseEntity<>(newWaterAnalysis, HttpStatus.CREATED);
        }catch (RetrofitException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/favorites/analysis")
    public ResponseEntity<UserFavoritesWaterAnalysisDto> getFavoriteFountainsAnalysis(@RequestBody UserFavoritesFountainsDto favoritesDto) {
        try {
            UserFavoritesWaterAnalysisDto analysisDto = waterAnalysisService.getFavoriteFountainsAnalysis(favoritesDto);
            return new ResponseEntity<>(analysisDto, HttpStatus.OK);
        } catch (RetrofitException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
