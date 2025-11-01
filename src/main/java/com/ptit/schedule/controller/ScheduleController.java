package com.ptit.schedule.controller;

import com.ptit.schedule.entity.Schedule;
import com.ptit.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/save-batch")
    public String saveBatch(@RequestBody List<Schedule> schedules) {
        scheduleService.saveAll(schedules);
        return "Đã lưu TKB vào database!";
    }
}