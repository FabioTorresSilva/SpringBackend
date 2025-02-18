package io.reflectoring.Sprint3SpringBoot.Retrofit.IRetrofit;

import io.reflectoring.Sprint3SpringBoot.Dto.DeviceDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Interface defining the API endpoints for managing water analyses.
 * This interface uses Retrofit to communicate with the server and perform CRUD operations on water analysis data.
 */
public interface IWaterAnalysisService {

    /**
     * Fetches all water analyses from the server.
     *
     * @return a {@link Call} object containing a list of {@link WaterAnalysisDto} representing all water analyses.
     */
    @GET()
    Call<List<WaterAnalysisDto>> getAllWaterAnalyses();

    /**
     * Fetches a specific water analysis by its unique identifier.
     *
     * @param id The unique identifier of the water analysis.
     * @return a {@link Call} object containing a {@link WaterAnalysisDto} representing the requested water analysis.
     */
    @GET("/{id}")
    Call<WaterAnalysisDto> getWaterAnalysisById(@Path("id") int id);

    /**
     * Updates an existing water analysis with the provided data.
     *
     * @param id The unique identifier of the water analysis to be updated.
     * @param waterAnalysis A {@link WaterAnalysisDto} object containing the updated water analysis data.
     * @return a {@link Call} object containing the updated {@link WaterAnalysisDto}.
     */
    @PUT("/{id}")
    Call<WaterAnalysisDto> updateWaterAnalysis(@Path("id") int id, @Body WaterAnalysisDto waterAnalysis);

    /**
     * Creates a new water analysis using the provided data.
     *
     * @param waterAnalysis A {@link WaterAnalysisDto} object containing the data for the new water analysis.
     * @return a {@link Call} object containing the created {@link WaterAnalysisDto}.
     */
    @POST()
    Call<WaterAnalysisDto> createWaterAnalysis(@Body WaterAnalysisDto waterAnalysis);
}
