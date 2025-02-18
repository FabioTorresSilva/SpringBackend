package io.reflectoring.Sprint3SpringBoot.Services.IServices;

import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Models.Tester;

import java.util.List;

/**
 * Interface for managing {@link Tester} entities.
 * This service provides methods to interact with tester data, including retrieving, updating, and creating testers,
 * as well as managing tester-specific favorites (water analyses).
 */
public interface ITesterService {

    /**
     * Retrieves a tester by their unique identifier.
     *
     * @param id The unique identifier of the tester to be retrieved.
     * @return The {@link Tester} object associated with the provided ID.
     */
    Tester getTesterById(int id);

    /**
     * Retrieves all testers in the system.
     *
     * @return A list of {@link Tester} objects representing all testers.
     */
    List<Tester> getAllTesters();

    /**
     * Updates an existing tester with the provided data.
     * The tester's ID should match the provided ID in order to successfully update the tester.
     *
     * @param id     The unique identifier of the tester to be updated.
     * @param tester The {@link Tester} object containing the updated data.
     * @return The updated {@link Tester} object.
     */
    Tester updateTester(int id, Tester tester);

    /**
     * Creates a new tester with the provided data.
     *
     * @param tester The {@link Tester} object to be created.
     * @return The newly created {@link Tester} object.
     */
    Tester createTester(Tester tester);

    /**
     * Retrieves a tester's list of favorite water analyses.
     * The method returns all water analyses marked as favorites by the specified tester.
     *
     * @param id The unique identifier of the tester whose favorites are being requested.
     * @return A list of {@link WaterAnalysisDto} objects representing the tester's favorite water analyses.
     */
    List<WaterAnalysisDto> getTesterWaterAnalysis(int id);

    /**
     * Adds a water analysis to the tester's list of favorite analyses.
     * This method validates the existence of the tester and water analysis before adding it to the tester's favorites.
     *
     * @param id                The unique identifier of the tester whose favorites list will be updated.
     * @param waterAnalysisDto The {@link WaterAnalysisDto} object representing the water analysis to be added.
     * @return The {@link WaterAnalysisDto} object that was added to the favorites list.
     */
    WaterAnalysisDto addWaterAnalysis(int id, WaterAnalysisDto waterAnalysisDto);
}
