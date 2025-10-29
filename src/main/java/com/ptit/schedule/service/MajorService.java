package com.ptit.schedule.service;

import com.ptit.schedule.dto.MajorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MajorService {
    List<MajorResponse> getAllMajors();
}
