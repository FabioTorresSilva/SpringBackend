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
}
