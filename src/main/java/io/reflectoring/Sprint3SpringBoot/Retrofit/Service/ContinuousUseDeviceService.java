package io.reflectoring.Sprint3SpringBoot.Retrofit.Service;

import io.reflectoring.Sprint3SpringBoot.Dto.ContinuousUseDeviceDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.IService.IContinuousUseDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * Service class for managing continuous use devices.
 * This class interacts with the Retrofit API client to perform CRUD operations.
 */
@Service
public class ContinuousUseDeviceService {

    private final IContinuousUseDeviceService continuousUseDeviceService;

    /**
     * Constructor for dependency injection.
     *
     * @param continuousUseDeviceService The Retrofit API client for continuous use devices.
     */
    @Autowired
    public ContinuousUseDeviceService(IContinuousUseDeviceService continuousUseDeviceService) {
        this.continuousUseDeviceService = continuousUseDeviceService;
    }

    /**
     * Retrieves all continuous use devices from the server.
     *
     * @return A list of {@link ContinuousUseDeviceDto} objects representing all continuous use devices.
     */
    public List<ContinuousUseDeviceDto> getAllContinuousUseDevices() {
        try {
            Response<List<ContinuousUseDeviceDto>> response = continuousUseDeviceService.getAllContinuousUseDevices().execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error fetching continuous use devices: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to fetch continuous use devices" + e);
        }
    }

    /**
     * Retrieves a continuous use device by its ID.
     *
     * @param id The unique identifier of the continuous use device.
     * @return A {@link ContinuousUseDeviceDto} representing the requested continuous use device.
     */
    public ContinuousUseDeviceDto getContinuousUseDeviceById(int id) {
        try {
            Response<ContinuousUseDeviceDto> response = continuousUseDeviceService.getContinuousUseDeviceById(id).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error fetching continuous use device with ID: " + id + ", Status Code: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to fetch continuous use device with ID: " + id + e);
        }
    }

    /**
     * Updates an existing continuous use device with the given data.
     *
     * @param id                    The unique identifier of the continuous use device.
     * @param continuousUseDeviceDto The updated {@link ContinuousUseDeviceDto} data.
     * @return The updated {@link ContinuousUseDeviceDto}.
     */
    public ContinuousUseDeviceDto updateContinuousUseDevice(int id, ContinuousUseDeviceDto continuousUseDeviceDto) {
        try {
            Response<ContinuousUseDeviceDto> response = continuousUseDeviceService.updateContinuousUseDevice(id, continuousUseDeviceDto).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error updating continuous use device with ID: " + id + ", Status Code: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to update continuous use device with ID: " + id + e);
        }
    }

    public ContinuousUseDeviceDto updateContinuousUseDeviceFrequency(int id, ContinuousUseDeviceDto continuousUseDeviceDto, int frequency) {
        try{
            Response<ContinuousUseDeviceDto> response = continuousUseDeviceService.updateFrequency(id, continuousUseDeviceDto, frequency).execute();
            if (response.isSuccessful()) {
                return response.body();
            }else {
                throw new RetrofitException("Error updating frequency from continuous use device with ID: \" + id + \", Status Code: \" + response.code()");
            }
        }catch (IOException e){
            throw new RetrofitException("Failed to update frequency from continuous use device with ID: " + id + e);
        }
    }

    /**
     * Creates a new continuous use device.
     *
     * @param continuousUseDeviceDto The {@link ContinuousUseDeviceDto} object to be created.
     * @return The newly created {@link ContinuousUseDeviceDto}.
     */
    public ContinuousUseDeviceDto createContinuousUseDevice(ContinuousUseDeviceDto continuousUseDeviceDto) {
        try {
            Response<ContinuousUseDeviceDto> response = continuousUseDeviceService.createContinuousUseDevice(continuousUseDeviceDto).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error creating continuous use device, Status Code: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Failed to create continuous use device" + e);
        }
    }
}
