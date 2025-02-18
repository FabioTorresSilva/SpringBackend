package io.reflectoring.Sprint3SpringBoot.Retrofit;

import io.reflectoring.Sprint3SpringBoot.Dto.DeviceDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.IRetrofit.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Service class for handling device-related operations using Retrofit.
 * It uses the {@link IDeviceService} interface to communicate with the API endpoints.
 */
@Service
public class DeviceService {

    private final IDeviceService deviceService;

    @Autowired
    public DeviceService(IDeviceService deviceService){
        this.deviceService = deviceService;
    }

    /**
     * Fetches a device by its unique identifier.
     *
     * @param deviceId The unique identifier of the device.
     * @return The {@link DeviceDto} object representing the device.
     */
    public DeviceDto getDeviceById(int deviceId){
        try {
            Response<DeviceDto> response = deviceService.getDeviceById(deviceId).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error fetching device by ID" + e);
        }
    }

    /**
     * Fetches all devices from the server.
     *
     * @return A list of {@link DeviceDto} objects representing all devices.
     */
    public List<DeviceDto> getAllDevices() {
        try {
            Response<List<DeviceDto>> response = deviceService.getAllDevices().execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error fetching all devices" + e);
        }
    }

    /**
     * Updates an existing device with the provided data.
     *
     * @param deviceId The unique identifier of the device to be updated.
     * @param device A {@link DeviceDto} object containing the updated data.
     * @return The updated {@link DeviceDto}.
     */
    public DeviceDto updateDevice(int deviceId, DeviceDto device) {
        try {
            Response<DeviceDto> response = deviceService.updateDevice(deviceId, device).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error updating device: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error updating device" + e);
        }
    }

    /**
     * Creates a new device with the provided data.
     *
     * @param device A {@link DeviceDto} object containing the new device data.
     * @return The created {@link DeviceDto}.
     */
    public DeviceDto createDevice(DeviceDto device) {
        try {
            Response<DeviceDto> response = deviceService.createDevice(device).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RetrofitException("Error creating device: " + response.code());
            }
        } catch (IOException e) {
            throw new RetrofitException("Error creating device" + e);
        }
    }
}
