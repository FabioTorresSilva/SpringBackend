package io.reflectoring.Sprint3SpringBoot.Services;

import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.ParamException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RepositoryException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Models.Tester;
import io.reflectoring.Sprint3SpringBoot.Repositories.TesterRepository;
import io.reflectoring.Sprint3SpringBoot.Services.IServices.ITesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing testers.
 * This class implements the methods defined in the {@link ITesterService} interface
 * to perform CRUD operations on testers.
 */
@Service
public class TesterService implements ITesterService {

    private final TesterRepository testerRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param testerRepository The repository for tester operations.
     */
    @Autowired
    public TesterService(TesterRepository testerRepository) {
        this.testerRepository = testerRepository;
    }

    /**
     * Retrieves a tester by their unique identifier.
     *
     * @param id The unique identifier of the tester.
     * @return A {@link Tester} object representing the tester.
     * @throws UserNotFoundException If the tester with the provided ID is not found.
     */
    @Override
    public Tester getClientById(int id) {
        Optional<Tester> tester = testerRepository.findById(id);

        if (tester.isEmpty()) {
            throw new UserNotFoundException("Tester with ID " + id + " not found.");
        }

        return tester.get();
    }

    /**
     * Retrieves all testers in the system.
     *
     * @return A list of {@link Tester} objects representing all testers.
     * @throws UserNotFoundException If no testers are found.
     */
    @Override
    public List<Tester> getAllClients() {
        List<Tester> testers = testerRepository.findAll();

        if (testers.isEmpty()) {
            throw new UserNotFoundException("No testers found.");
        }

        return testers;
    }

    /**
     * Updates an existing tester with the provided data.
     * Ensures the provided ID matches the tester's ID before updating.
     *
     * @param id     The unique identifier of the tester to be updated.
     * @param tester The {@link Tester} object containing the updated data.
     * @return The updated {@link Tester} object.
     * @throws ParamException If the provided ID does not match the tester's ID.
     * @throws RepositoryException If an error occurs while saving the updated tester.
     */
    @Override
    public Tester updateClient(int id, Tester tester) {

        if (id != tester.getId()) {
            throw new ParamException("IDs do not match.");
        }

        Tester updateTester = getClientById(id);

        updateTester.setName(tester.getName());
        updateTester.setEmail(tester.getEmail());
        updateTester.setPassword(tester.getPassword());

        try {
            testerRepository.save(updateTester);
        } catch (Exception e) {
            throw new RepositoryException("Error updating tester in repository.");
        }

        return updateTester;
    }

    /**
     * Creates a new tester with the provided data.
     *
     * @param tester The {@link Tester} object to be created.
     * @return The newly created {@link Tester} object.
     * @throws RepositoryException If an error occurs while saving the tester.
     */
    @Override
    public Tester createClient(Tester tester) {

        try {
            testerRepository.save(tester);
        } catch (Exception e) {
            throw new RepositoryException("Error creating tester in repository.");
        }

        return tester;
    }

    /**
     * Retrieves a tester's list of favorite water analyses.
     *
     * @param id The unique identifier of the tester.
     * @return A list of {@link WaterAnalysisDto} objects representing the tester's favorite water analyses.
     * @throws UserNotFoundException If the tester with the provided ID is not found.
     */
    @Override
    public List<WaterAnalysisDto> getTesterWaterAnalysis(int id) {
        Tester tester = getClientById(id);
        return tester.getWaterAnalysis();
    }

    /**
     * Adds a water analysis to the tester's list of favorites.
     * Ensures that the tester and water analysis exist before adding.
     *
     * @param id               The unique identifier of the tester.
     * @param waterAnalysisDto The {@link WaterAnalysisDto} object representing the water analysis to be added.
     * @return The {@link WaterAnalysisDto} object that was added to the favorites list.
     * @throws ParamException If the water analysis is null.
     * @throws UserNotFoundException If the tester with the provided ID is not found.
     */
    @Override
    public WaterAnalysisDto addWaterAnalysis(int id, WaterAnalysisDto waterAnalysisDto) {

        if (waterAnalysisDto == null) {
            throw new ParamException("Water analysis does not exist.");
        }

        Optional<Tester> tester = testerRepository.findById(id);

        if (tester.isEmpty()) {
            throw new UserNotFoundException("Tester with ID " + id + " not found.");
        }

        Tester tst = tester.get();
        tst.waterAnalysis.add(waterAnalysisDto);

        return waterAnalysisDto;
    }
}
