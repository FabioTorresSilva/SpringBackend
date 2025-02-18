package io.reflectoring.Sprint3SpringBoot.Retrofit.Service;

import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.IService.IWaterAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Service class for managing water analyses.
 * This class interacts with the Retrofit API client to perform CRUD operations.
 */
@Service
public class WaterAnalysisService {

    private final IWaterAnalysisService waterAnalysisService;

    /**
     * Constructor for dependency injection.
     *
     * @param waterAnalysisService The Retrofit API client for water analyses.
     */
    @Autowired
    public WaterAnalysisService(IWaterAnalysisService waterAnalysisService) {
        this.waterAnalysisService = waterAnalysisService;
    }

    /**
     * Retrieves all water analyses from the server.
     *
     * @return A list of {@link WaterAnalysisDto} objects representing all water analyses.
     */
    public List<WaterAnalysisDto> getAllWaterAnalyses() {
        try {
            Response<List<WaterAnalysisDto>> response = waterAnalysisService.getAllWaterAnalyses().execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error fetching water analyses: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to fetch water analyses" + e);
        }
    }

    /**
     * Retrieves a water analysis by its ID.
     *
     * @param id The unique identifier of the water analysis.
     * @return A {@link WaterAnalysisDto} representing the requested water analysis.
     */
    public WaterAnalysisDto getWaterAnalysisById(int id) {
        try {
            Response<WaterAnalysisDto> response = waterAnalysisService.getWaterAnalysisById(id).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error fetching water analysis with ID: " + id + ", Status Code: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to fetch water analysis with ID: " + id + e);
        }
    }

    /**
     * Updates an existing water analysis with the given data.
     *
     * @param id            The unique identifier of the water analysis.
     * @param waterAnalysis The updated {@link WaterAnalysisDto} data.
     * @return The updated {@link WaterAnalysisDto}.
     */
    public WaterAnalysisDto updateWaterAnalysis(int id, WaterAnalysisDto waterAnalysis) {
        try {
            Response<WaterAnalysisDto> response = waterAnalysisService.updateWaterAnalysis(id, waterAnalysis).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error updating water analysis with ID: " + id + ", Status Code: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to update water analysis with ID: " + id + e);
        }
    }

    /**
     * Creates a new water analysis.
     *
     * @param waterAnalysis The {@link WaterAnalysisDto} object to be created.
     * @return The newly created {@link WaterAnalysisDto}.
     */
    public WaterAnalysisDto createWaterAnalysis(WaterAnalysisDto waterAnalysis) {
        try {
            Response<WaterAnalysisDto> response = waterAnalysisService.createWaterAnalysis(waterAnalysis).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error creating water analysis, Status Code: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to create water analysis" + e);
        }
    }
}
