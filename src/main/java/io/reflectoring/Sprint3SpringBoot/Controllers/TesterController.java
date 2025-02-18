package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.UserDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserFullDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.ParamException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RepositoryException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Mapper.UsersMapper;
import io.reflectoring.Sprint3SpringBoot.Models.Tester;
import io.reflectoring.Sprint3SpringBoot.Services.TesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing testers.
 * <p>
 * Provides endpoints for retrieving, creating, updating, and managing testers.
 * </p>
 */
@RestController
@RequestMapping("/api/tester")
public class TesterController {

    private final TesterService testerService;
    private final UsersMapper usersMapper;

    /**
     * Constructor for dependency injection.
     *
     * @param testerService The service handling tester operations.
     */
    @Autowired
    public TesterController(TesterService testerService, UsersMapper usersMapper) {
        this.testerService = testerService;
        this.usersMapper = usersMapper;
    }

    /**
     * Retrieves a tester by their unique ID.
     *
     * @param id The ID of the tester.
     * @return A {@link ResponseEntity} containing the {@link Tester} if found,
     *         or an error message with the appropriate HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTesterById(@PathVariable int id) {
        try {
            Tester tester = testerService.getTesterById(id);

            return ResponseEntity.ok(usersMapper.TesterToDto(tester));

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Retrieves all testers.
     *
     * @return A {@link ResponseEntity} containing a list of {@link Tester} objects
     *         or an error message if no testers are found.
     */
    @GetMapping
    public ResponseEntity<?> getAllTesters() {
        try {
            List<Tester> testers = testerService.getAllTesters();
            List<UserDto> testerDtos = new ArrayList<>();

            for (Tester tester : testers) {
                testerDtos.add(usersMapper.TesterToDto(tester));
            }

            return ResponseEntity.ok(testerDtos);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Updates an existing tester.
     *
     * @param id     The ID of the tester to update.
     * @param userDto The updated tester details.
     * @return A {@link ResponseEntity} containing the updated tester or an error message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTester(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            Tester updatedTester = testerService.getTesterById(id);
            updatedTester.name = userDto.name;
            updatedTester.email = userDto.email;

            testerService.updateTester(id, updatedTester);

            return ResponseEntity.ok(userDto);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (ParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Creates a new tester.
     *
     * @param userFullDto The tester details.
     * @return A {@link ResponseEntity} containing the created tester or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createTester(@RequestBody UserFullDto userFullDto) {
        try {
            Tester newTester = new Tester();
            newTester.name = userFullDto.name;
            newTester.email = userFullDto.email;
            newTester.password = userFullDto.password;
            newTester.role = userFullDto.role;

            testerService.createTester(newTester);
            return ResponseEntity.status(HttpStatus.CREATED).body(usersMapper.TesterToDto(newTester));
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Retrieves a tester's list of favorite water analyses.
     *
     * @param id The ID of the tester.
     * @return A {@link ResponseEntity} containing the list of favorite water analyses
     *         or an error message if the tester is not found.
     */
    @GetMapping("/waterAnalysis/{id}")
    public ResponseEntity<?> getTesterWaterAnalysis(@PathVariable int id) {
        try {
            return ResponseEntity.ok(testerService.getTesterWaterAnalysis(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Adds a water analysis to the tester's list of favorites.
     *
     * @param id               The ID of the tester.
     * @param waterAnalysisDto The water analysis details.
     * @return A {@link ResponseEntity} containing the added water analysis or an error message.
     */
    @PutMapping("/addWaterAnalysis/{id}")
    public ResponseEntity<?> addWaterAnalysis(@PathVariable int id, @RequestBody WaterAnalysisDto waterAnalysisDto) {
        try {
            return ResponseEntity.ok(testerService.addWaterAnalysis(id, waterAnalysisDto));

        } catch (UserNotFoundException | ParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
