package com.ptit.schedule.service.impl;

import com.ptit.schedule.entity.Schedule;
import com.ptit.schedule.repository.ScheduleRepository;
import com.ptit.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void saveAll(List<Schedule> schedules) {
        scheduleRepository.saveAll(schedules);
    }
}