package io.reflectoring.Sprint3SpringBoot.Retrofit.IService;

import io.reflectoring.Sprint3SpringBoot.Dto.ContinuousUseDeviceDto;
import jdk.jfr.Frequency;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Interface defining API endpoints for managing continuous use devices.
 * This interface uses Retrofit to communicate with the server and perform CRUD operations.
 */
public interface IContinuousUseDeviceService {

    /**
     * Retrieves a list of all continuous use devices from the server.
     *
     * @return a {@link Call} object containing a list of {@link ContinuousUseDeviceDto}.
     */
    @GET("continuoususedevice")
    Call<List<ContinuousUseDeviceDto>> getAllContinuousUseDevices();

    /**
     * Retrieves a specific continuous use device by its unique identifier.
     *
     * @param id The unique identifier of the continuous use device.
     * @return a {@link Call} object containing the requested {@link ContinuousUseDeviceDto}.
     */
    @GET("continuoususedevice/{id}")
    Call<ContinuousUseDeviceDto> getContinuousUseDeviceById(@Path("id") int id);

    /**
     * Updates an existing continuous use device with the provided data.
     *
     * @param id The unique identifier of the continuous use device to be updated.
     * @param continuousUseDeviceDto A {@link ContinuousUseDeviceDto} object containing the updated data.
     * @return a {@link Call} object containing the updated {@link ContinuousUseDeviceDto}.
     */
    @PUT("continuoususedevice/{id}")
    Call<ContinuousUseDeviceDto> updateContinuousUseDevice(@Path("id") int id, @Body ContinuousUseDeviceDto continuousUseDeviceDto);

    /**
     * Updates the frequency from a continuous use device with the provided data.
     *
     * @param id The unique identifier of the continuous use device to be updated.
     * @param frequency The frequency that will be changed.
     * @return a {@link Call} object containing the updated {@link ContinuousUseDeviceDto} with new frequency.
      */
    @PUT("continuoususedevice/{deviceId}/periodicity")
    Call<ContinuousUseDeviceDto> updateFrequency(@Path("deviceId") int id, @Body int frequency);

    /**
     * Creates a new continuous use device using the provided data.
     *
     * @param continuousUseDeviceDto A {@link ContinuousUseDeviceDto} object containing the data for the new device.
     * @return a {@link Call} object containing the created {@link ContinuousUseDeviceDto}.
     */
    @POST("continuoususedevice")
    Call<ContinuousUseDeviceDto> createContinuousUseDevice(@Body ContinuousUseDeviceDto continuousUseDeviceDto);
}
