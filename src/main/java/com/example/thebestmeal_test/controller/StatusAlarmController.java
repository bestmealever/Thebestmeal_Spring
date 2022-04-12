package com.example.thebestmeal_test.controller;

import com.example.thebestmeal_test.domain.StatusAlarm;
import com.example.thebestmeal_test.dto.StatusAlarmDto;
import com.example.thebestmeal_test.service.StatusAlarmService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StatusAlarmController {

    private final StatusAlarmService statusAlarmService;
    private final ModelMapper modelMapper;

    @PostMapping("/statusAlarm")
    public StatusAlarm setStatusAlarm(StatusAlarmDto.Request request) throws IOException {
        return statusAlarmService.setStatusAlarm(request);
    }

    @GetMapping("/statusAlarms")
    public List<StatusAlarmDto.Response> getStatusAlarms() {
//        int i = 1/0;
        List<StatusAlarm> statusAlarms = statusAlarmService.getStatusAlarms();
        List<StatusAlarmDto.Response> response = modelMapper.map(statusAlarms, new TypeToken<List<StatusAlarmDto.Response>>() {}.getType());
        return response;
    }
}
