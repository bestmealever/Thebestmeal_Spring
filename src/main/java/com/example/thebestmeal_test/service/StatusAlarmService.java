package com.example.thebestmeal_test.service;

import com.example.thebestmeal_test.domain.StatusAlarm;
import com.example.thebestmeal_test.dto.StatusAlarmDto;
import com.example.thebestmeal_test.repository.StatusAlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusAlarmService {

    private final StatusAlarmRepository statusAlarmRepository;
    private final AwsService awsService;

    @Transactional
    public StatusAlarm setStatusAlarm(StatusAlarmDto.Request request) throws IOException {
        String url = null;
        if(request.getImage() != null) url = awsService.upload(request.getImage());
        StatusAlarm statusAlarm = new StatusAlarm(request, url);
        statusAlarmRepository.save(statusAlarm);

        return statusAlarm;
    }

    public StatusAlarm getStatusAlarm(Long id) {
        return statusAlarmRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<StatusAlarm> getStatusAlarms() {
        return statusAlarmRepository.findAll();
    }
}
