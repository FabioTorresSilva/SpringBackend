package io.reflectoring.Sprint3SpringBoot.Retrofit.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.DeviceDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {


    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<DeviceDto>> getAllDevices() {
        try{
            List<DeviceDto> devices = deviceService.getAllDevices();
            return devices.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(devices, HttpStatus.OK);
        }catch (RetrofitException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@RequestParam("id") Integer id) {
        if (id <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }try{
            DeviceDto device = deviceService.getDeviceById(id);
            return new ResponseEntity<>(device, HttpStatus.OK);
        }catch (RetrofitException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto device) {
        try{
            DeviceDto deviceCreated = deviceService.createDevice(device);
            return new ResponseEntity<>(deviceCreated, HttpStatus.CREATED);
        }catch (RetrofitException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@RequestBody DeviceDto device, @PathVariable int id) {
        if(id <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }try{
            DeviceDto updatedDevice = deviceService.updateDevice(id, device);
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        }catch (RetrofitException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
