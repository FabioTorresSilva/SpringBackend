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

    @GetMapping("/all-continuous-use")
    public ResponseEntity<List<ContinuousUseDeviceDto>> getAllContinuousUseDevices() {
        try {
            List<ContinuousUseDeviceDto> devices = ContinuousUseDeviceService.getAllContinuousUseDevices();
            return ResponseEntity.ok(devices);
        } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/continuous-use/{id}")
    public ResponseEntity<String> getContinuousUseDeviceById(@PathVariable("id") int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID. ID must be greater than zero.");
        }
        try{
            ContinuousUseDeviceDto continuousUseDeviceDto = continuousUseDeviceService.getContinuousUseDeviceById(id);
            if(continuousUseDeviceDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device not found.");
            }
            return ResponseEntity.ok(continuousUseDeviceDto.toString());
            } catch (RetrofitException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
         }
    }
}
