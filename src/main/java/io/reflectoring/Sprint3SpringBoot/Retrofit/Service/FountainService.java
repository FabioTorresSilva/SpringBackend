package io.reflectoring.Sprint3SpringBoot.Retrofit.Service;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.IService.IFountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Service class for handling fountain-related operations using Retrofit.
 * It uses the {@link IFountainService} interface to communicate with the API endpoints.
 */
@Service
public class FountainService {

    private final IFountainService fountainService;

    /**
     * Constructor to inject the Retrofit service interface.
     *
     * @param fountainService The {@link IFountainService} instance used for API calls.
     */
    @Autowired
    public FountainService(IFountainService fountainService) {
        this.fountainService = fountainService;
    }

    /**
     * Fetches a list of all fountains from the server.
     *
     * @return A list of {@link FountainDto} objects representing all fountains.
     */
    public List<FountainDto> getAllFountains() {
        try {
            Response<List<FountainDto>> response = fountainService.getAllFountains().execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error fetching fountains: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error retrieving fountains" + e);
        }
    }

    /**
     * Fetches a specific fountain by its unique identifier.
     *
     * @param fountainId The unique identifier of the fountain.
     * @return The {@link FountainDto} object representing the requested fountain.
     */
    public FountainDto getFountainById(int fountainId) {
        try {
            Response<FountainDto> response = fountainService.getFountainById(fountainId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error fetching fountain: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error retrieving fountain" + e);
        }
    }

    /**
     * Updates an existing fountain with the provided data.
     *
     * @param fountainId The unique identifier of the fountain to be updated.
     * @param fountain A {@link FountainDto} object containing the updated data.
     * @return The updated {@link FountainDto}.
     */
    public FountainDto updateFountain(int fountainId, FountainDto fountain) {
        try {
            Response<FountainDto> response = fountainService.updateFountain(fountainId, fountain).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error updating fountain: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error updating fountain" + e);
        }
    }

    /**
     * Creates a new fountain using the provided data.
     *
     * @param fountain A {@link FountainDto} object containing the data for the new fountain.
     * @return The created {@link FountainDto}.
     */
    public FountainDto createFountain(FountainDto fountain) {
        try {
            Response<FountainDto> response = fountainService.createFountain(fountain).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error creating fountain: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error creating fountain" + e);
        }
    }

    /**
     * Deletes a specific fountain by its unique identifier.
     *
     * @param fountainId The unique identifier of the fountain to be deleted.
     * @return The deleted {@link FountainDto}, if successful.
     */
    public Boolean deleteFountain(int fountainId) {
        try {
            Response<Boolean> response = fountainService.deleteFountain(fountainId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error deleting fountain: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error deleting fountain" + e);
        }
    }
}
