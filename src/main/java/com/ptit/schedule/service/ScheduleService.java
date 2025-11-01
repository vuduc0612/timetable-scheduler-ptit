package com.ptit.schedule.service;

import com.ptit.schedule.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    void saveAll(List<Schedule> schedules);
}