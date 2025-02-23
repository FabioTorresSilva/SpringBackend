package io.reflectoring.Sprint3SpringBoot.Retrofit.IService;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Interface defining the API endpoints for managing fountains.
 * This interface uses Retrofit to communicate with the server and perform CRUD operations on fountains.
 */
public interface IFountainService {

    /**
     * Fetches all fountains from the server.
     *
     * @return a {@link Call} object containing a list of {@link FountainDto} representing all fountains.
     */
    @GET("fountains")
    Call<List<FountainDto>> getAllFountains();

    /**
     * Fetches a specific fountain by its unique identifier.
     *
     * @param id The unique identifier of the fountain.
     * @return a {@link Call} object containing a {@link FountainDto} representing the requested fountain.
     */
    @GET("fountains/{id}")
    Call<FountainDto> getFountainById(@Path("id") int id);

    /**
     * Updates an existing fountain with the provided data.
     *
     * @param id The unique identifier of the fountain to be updated.
     * @param fountain A {@link FountainDto} object containing the updated fountain data.
     * @return a {@link Call} object containing the updated {@link FountainDto}.
     */
    @PUT("fountains/{id}")
    Call<FountainDto> updateFountain(@Path("id") int id, @Body FountainDto fountain);

    /**
     * Creates a new fountain using the provided data.
     *
     * @param fountain A {@link FountainDto} object containing the data for the new fountain.
     * @return a {@link Call} object containing the created {@link FountainDto}.
     */
    @POST("fountains")
    Call<FountainDto> createFountain(@Body FountainDto fountain);

    /**
     * Creates a continuous use device to a fountain.
     * @param fountainId The unique identifier of the fountain.
     * @param deviceId The unique identifier of the continuous use device to be added to the fountain.
     * @return a {@link Call} object containing the created {@link FountainDto} with the continuous use device associated.
     */
    @POST("fountains/{id}/device/{id}")
    Call<FountainDto> addContinuousUseDeviceToFountain(@Path("id")int fountainId, @Path("id") int deviceId);

    /**
     * Deletes a specific fountain by its unique identifier.
     *
     * @param id The unique identifier of the fountain to be deleted.
     * @return a {@link Call} object containing the deleted {@link FountainDto}, if successful.
     */
    @DELETE("fountains/{id}")
    Call<Boolean> deleteFountain(@Path("id") int id);

    /**
     * Deletes the device associated with a specified fountain.
     * <p>
     * This method removes the device linked to the fountain identified by the provided unique fountain identifier.
     * Upon successful deletion, the server returns the updated details of the fountain.
     * </p>
     *
     * @param fountainId the unique identifier of the fountain from which the device will be removed.
     * @return a {@link Call} object containing the updated {@link FountainDto} after the device removal.
     */
    @DELETE("fountains/{fountainId}/device")
    Call<FountainDto> deleteDeviceFromFountain(@Path("fountainId") int fountainId);

    /**
     * Searches for fountains based on the provided query.
     * <p>
     * This method sends a GET request to retrieve a list of fountains that match the search query.
     * The query parameter is passed as a query string, and the server returns a list of matching fountain details.
     * </p>
     *
     * @param query the search term used to filter the fountains.
     * @return a {@link Call} object containing a list of {@link FountainDto} representing the fountains that match the search query.
     */
    @GET("fountains/search")
    Call<List<FountainDto>> searchFountains(@Query("q") String query);
}
