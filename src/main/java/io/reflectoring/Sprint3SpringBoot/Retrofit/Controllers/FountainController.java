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

    @PostMapping("/{fountainId}/device/{deviceId}")
    public ResponseEntity<FountainDto> addContinuousUseDeviceToFountain(@PathVariable int fountainId, @PathVariable int deviceId) {
        try{
            FountainDto addedContinuousDevice = fountainService.addContinuousUseDeviceToFountain(fountainId, deviceId);
            return new ResponseEntity<>(addedContinuousDevice, HttpStatus.CREATED);
        }catch (RetrofitException e){
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

    /**
     * Removes the device associated with a specific fountain.
     * <p>
     * This endpoint deletes the device linked to the fountain identified by the given fountain ID.
     * It validates the provided fountain ID and returns a BAD_REQUEST (400) response if the ID is not greater than zero.
     * Upon a successful deletion, the updated fountain details are returned with an OK (200) status.
     * If no fountain is found with the specified ID, a NOT_FOUND (404) status is returned.
     * In case of a server-side error during the deletion process, an INTERNAL_SERVER_ERROR (500) status is returned.
     * </p>
     *
     * @param fountainId the unique identifier of the fountain from which the device should be removed.
     * @return a {@link ResponseEntity} containing the updated {@link FountainDto} and an appropriate HTTP status code.
     */
    @DeleteMapping("/{fountainId}/device")
    public ResponseEntity<FountainDto> deleteDeviceFromFountain(@PathVariable int fountainId) {
        if (fountainId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            FountainDto updatedFountain = fountainService.deleteDeviceFromFountain(fountainId);
            return updatedFountain != null
                    ? ResponseEntity.ok(updatedFountain)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Searches for fountains based on the provided query parameter.
     * <p>
     * This endpoint handles GET requests to "/search". It accepts a query parameter "q" used for filtering fountains.
     * The method invokes the fountain service to retrieve a list of matching fountains. If no fountains match the query,
     * it returns an HTTP 204 (No Content) response. Otherwise, it responds with an HTTP 200 (OK) status and includes the
     * list of fountains along with the "Access-Control-Allow-Origin" header set to "http://localhost:4200" for CORS support.
     * In case of a RetrofitException, an HTTP 500 (Internal Server Error) is returned along with an empty list.
     * </p>
     *
     * @param query the search term used to filter fountains.
     * @return a {@link ResponseEntity} containing the list of {@link FountainDto} matching the search criteria or an appropriate HTTP status code.
     */
    @GetMapping("/search")
    public ResponseEntity<List<FountainDto>> searchFountains(@RequestParam("q") String query) {
        try {
            List<FountainDto> fountains = fountainService.searchFountains(query);
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

    /**
     * Updates the susceptibility information of a specified fountain.
     * <p>
     * This endpoint handles HTTP PUT requests to update the susceptibility data of the fountain identified by the provided fountain ID.
     * It expects the fountain ID as a path variable and the updated fountain data in the request body.
     * <br>
     * The method performs the following operations:
     * <ul>
     *   <li>Validates that the fountain ID is greater than zero. If not, it returns a 400 BAD_REQUEST response.</li>
     *   <li>Invokes the service layer to update the fountain's susceptibility details using the provided {@link FountainDto}.</li>
     *   <li>If the fountain is successfully updated and exists, it returns a 200 OK response with the updated fountain details.</li>
     *   <li>If the fountain is not found, it returns a 404 NOT_FOUND response.</li>
     *   <li>If an error occurs during the update process (captured as a {@link RetrofitException}), it returns a 500 INTERNAL_SERVER_ERROR response.</li>
     * </ul>
     * </p>
     *
     * @param fountainId the unique identifier of the fountain to be updated; must be greater than zero.
     * @param fountain a {@link FountainDto} object containing the updated susceptibility and other fountain details.
     * @return a {@link ResponseEntity} containing the updated {@link FountainDto} if successful, or an appropriate HTTP status code indicating the result of the operation.
     */
    @PutMapping("/{fountainId}/susceptibility")
    public ResponseEntity<FountainDto> updateFountainSusceptibility(@PathVariable int fountainId, @RequestBody FountainDto fountain) {
        if (fountainId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            FountainDto updatedFountain = fountainService.updateFountainSusceptibility(fountainId, fountain);
            return updatedFountain != null
                    ? ResponseEntity.ok(updatedFountain)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
