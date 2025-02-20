package io.reflectoring.Sprint3SpringBoot.Retrofit.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.ContinuousUseDeviceDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RetrofitException;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.ContinuousUseDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/continuousUseDevice")
public class ContinuousUseDeviceController {

    private final ContinuousUseDeviceService continuousUseDeviceService;

    @Autowired
    public ContinuousUseDeviceController(ContinuousUseDeviceService continuousUseDeviceService) {
        this.continuousUseDeviceService = continuousUseDeviceService;
    }

    @GetMapping
    public ResponseEntity<List<ContinuousUseDeviceDto>> getAllContinuousUseDevices() {
        try {
            List<ContinuousUseDeviceDto> devices = ContinuousUseDeviceService.getAllContinuousUseDevices();
            return ResponseEntity.ok(devices);
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContinuousUseDeviceDto> getContinuousUseDeviceById(@PathVariable("id") int deviceId) {
        if (deviceId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            ContinuousUseDeviceDto continuousUseDeviceDto = continuousUseDeviceService.getContinuousUseDeviceById(deviceId);
            return continuousUseDeviceDto != null ? ResponseEntity.ok(continuousUseDeviceDto) : ResponseEntity.notFound().build();
        }catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ContinuousUseDeviceDto> createContinuousUseDevice(@RequestBody ContinuousUseDeviceDto continuousUseDeviceDto) {
        try{
            ContinuousUseDeviceDto continuousUseDeviceDtoCreated = continuousUseDeviceService.createContinuousUseDevice(continuousUseDeviceDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(continuousUseDeviceDtoCreated);
        }catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("id")
    public ResponseEntity<ContinuousUseDeviceDto> updateContinuousUseDevice(@RequestBody ContinuousUseDeviceDto continuousUseDeviceDto, @PathVariable("id") int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            ContinuousUseDeviceDto continuousUseDeviceDtoUpdated = continuousUseDeviceService.updateContinuousUseDevice(id, continuousUseDeviceDto);
            return ResponseEntity.ok(continuousUseDeviceDtoUpdated);
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
