package io.reflectoring.Sprint3SpringBoot.Retrofit.IService;

import io.reflectoring.Sprint3SpringBoot.Dto.DeviceDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Interface defining the API endpoints for managing devices.
 * This interface uses Retrofit to communicate with the server and perform CRUD operations on devices.
 */
public interface IDeviceService {

    /**
     * Fetches all devices from the server.
     *
     * @return a {@link Call} object containing a list of {@link DeviceDto} representing all devices.
     */
    @GET("devices")
    Call<List<DeviceDto>> getAllDevices();

    /**
     * Fetches a specific device by its unique identifier.
     *
     * @param id The unique identifier of the device.
     * @return a {@link Call} object containing a {@link DeviceDto} representing the requested device.
     */
    @GET("devices/{id}")
    Call<DeviceDto> getDeviceById(@Path("id") int id);

    /**
     * Updates an existing device with the provided data.
     *
     * @param id     The unique identifier of the device to be updated.
     * @param device A {@link DeviceDto} object containing the updated device data.
     * @return a {@link Call} object containing the updated {@link DeviceDto}.
     */
    @PUT("devices/{id}")
    Call<DeviceDto> updateDevice(@Path("id") int id, @Body DeviceDto device);

    /**
     * Creates a new device using the provided data.
     *
     * @param device A {@link DeviceDto} object containing the data for the new device.
     * @return a {@link Call} object containing the created {@link DeviceDto}.
     */
    @POST("devices")
    Call<DeviceDto> createDevice(@Body DeviceDto device);
}
