package io.reflectoring.Sprint3SpringBoot.Retrofit.Service;

import io.reflectoring.Sprint3SpringBoot.Dto.ContinuousUseDeviceDto;
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
                throw new RetrofitException("Error fetching fountains " + response.code());
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

    public FountainDto addContinuousUseDeviceToFountain(int fountainId, int deviceId) {
        if (fountainId <= 0 || deviceId <= 0) {
            throw new IllegalArgumentException("The IDs must be higher than zero.");
        }

        try {
            Response<FountainDto> response = fountainService.addContinuousUseDeviceToFountain(fountainId, deviceId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error associating device: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error associating device: " + e.getMessage());
        }
    }

//    public FountainDto addContinuousUseDeviceToFountain(int fountainId, int deviceId) {
//
//    }

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

    /**
     * Removes the device associated with the specified fountain.
     * <p>
     * This method validates that the given fountain ID is greater than zero. It then initiates a Retrofit call to delete the device
     * linked to the fountain identified by the fountain ID. If the call is successful, the method returns the updated fountain details.
     * In case of an unsuccessful response or an I/O error during the operation, a RetrofitException is thrown with an appropriate error message.
     * </p>
     *
     * @param fountainId the unique identifier of the fountain from which the device should be removed; must be greater than zero.
     * @return the updated {@link FountainDto} reflecting the fountain after the device has been removed.
     * @throws IllegalArgumentException if the fountainId is less than or equal to zero.
     * @throws RetrofitException if the deletion operation fails due to an unsuccessful response or an I/O error.
     */
    public FountainDto deleteDeviceFromFountain(int fountainId) {
        if (fountainId <= 0) {
            throw new IllegalArgumentException("The fountain ID must be greater than zero.");
        }
        try {
            Response<FountainDto> response = fountainService.deleteDeviceFromFountain(fountainId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error removing device from fountain: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error removing device from fountain: " + e.getMessage());
        }
    }

    /**
     * Searches for fountains using the specified query string.
     * <p>
     * This method makes a synchronous Retrofit call to the fountain service to retrieve a list of fountains that match the provided query.
     * If the HTTP response is successful, it returns the list of {@link FountainDto} objects.
     * If the response is unsuccessful or if an I/O error occurs, a {@link RetrofitException} is thrown with an appropriate error message.
     * </p>
     *
     * @param query the search term used to filter the fountains.
     * @return a {@link List} of {@link FountainDto} objects that match the search criteria.
     * @throws RetrofitException if the HTTP response is unsuccessful or an I/O error occurs during the execution of the request.
     */
    public List<FountainDto> searchFountains(String query) {
        try {
            Response<List<FountainDto>> response = fountainService.searchFountains(query).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error searching fountains: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error searching fountains: " + e.getMessage());
        }
    }

    /**
     * Updates the susceptibility details of a specified fountain.
     * <p>
     * This method validates that the provided fountain ID is greater than zero. It then performs a synchronous Retrofit call to update
     * the susceptibility information of the fountain using the provided {@link FountainDto} data. If the HTTP response is successful,
     * the updated fountain details are returned. Otherwise, if the response is unsuccessful or an I/O error occurs, a {@link RetrofitException}
     * is thrown with an appropriate error message.
     * </p>
     *
     * @param fountainId the unique identifier of the fountain whose susceptibility information is to be updated;
     *                   must be greater than zero.
     * @param fountain   a {@link FountainDto} object containing the updated susceptibility and other fountain details.
     * @return the updated {@link FountainDto} reflecting the new susceptibility details.
     * @throws IllegalArgumentException if the fountainId is less than or equal to zero.
     * @throws RetrofitException if the update operation fails due to an unsuccessful HTTP response or an I/O error.
     */
    public FountainDto updateFountainSusceptibility(int fountainId, FountainDto fountain) {
        if (fountainId <= 0) {
            throw new IllegalArgumentException("The fountain ID must be greater than zero.");
        }
        try {
            Response<FountainDto> response = fountainService.updateFountainSusceptibility(fountainId, fountain).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error updating fountain susceptibility: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error updating fountain susceptibility: " + e.getMessage());
        }
    }

    /**
     * Updates the device associated with a specific fountain.
     * <p>
     * This method updates the device for the fountain identified by the provided fountainId.
     * The new device to be associated with the fountain is specified by newDeviceId.
     * The method performs the following steps:
     * <ul>
     *   <li>Validates that both <code>fountainId</code> and <code>newDeviceId</code> are greater than zero; otherwise, it throws an {@link IllegalArgumentException}.</li>
     *   <li>Executes a synchronous Retrofit call to update the device associated with the fountain.</li>
     *   <li>If the HTTP response is successful, it returns the updated {@link FountainDto} with the new device details.</li>
     *   <li>If the response is unsuccessful, it throws a {@link RetrofitException} with the HTTP status code.</li>
     *   <li>If an {@link IOException} occurs during the request, it throws a {@link RetrofitException} with the error message.</li>
     * </ul>
     * </p>
     *
     * @param fountainId the unique identifier of the fountain to be updated; must be greater than zero.
     * @param newDeviceId the unique identifier of the new device to be associated with the fountain; must be greater than zero.
     * @return the updated {@link FountainDto} reflecting the fountain after the device update.
     * @throws IllegalArgumentException if either <code>fountainId</code> or <code>newDeviceId</code> is less than or equal to zero.
     * @throws RetrofitException if the update operation fails due to an unsuccessful HTTP response or an I/O error.
     */
    public FountainDto updateDeviceForFountain(int fountainId, int newDeviceId) {
        if (fountainId <= 0 || newDeviceId <= 0) {
            throw new IllegalArgumentException("IDs must be greater than zero.");
        }
        try {
            Response<FountainDto> response = fountainService.updateDeviceForFountain(fountainId, newDeviceId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error updating device for fountain: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error updating device for fountain: " + e.getMessage());
        }
    }

    /**
     * Retrieves water analysis data for the specified fountain.
     *
     * @param fountainId the unique identifier of the fountain.
     * @return the FountainDto containing water analysis information.
     * @throws RetrofitException if an error occurs during the API call.
     */
    public FountainDto getWaterAnalysisForFountain(int fountainId) {
        if (fountainId <= 0) {
            throw new IllegalArgumentException("The fountain ID must be greater than zero.");
        }
        try {
            Response<FountainDto> response = fountainService.getWaterAnalysisForFountain(fountainId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error getting water analysis: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error getting water analysis: " + e.getMessage());
        }
    }
}
