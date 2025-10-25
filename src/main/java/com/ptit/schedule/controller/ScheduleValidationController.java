package com.ptit.schedule.controller;

import com.ptit.schedule.dto.ConflictResult;
import com.ptit.schedule.dto.ScheduleEntry;
import com.ptit.schedule.service.ScheduleConflictDetectionService;
import com.ptit.schedule.service.ScheduleExcelReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/schedule-validation")
@RequiredArgsConstructor
public class ScheduleValidationController {

    private final ScheduleExcelReaderService excelReaderService;
    private final ScheduleConflictDetectionService conflictDetectionService;

    /**
     * Trang upload file Excel
     */
    @GetMapping
    public String uploadPage() {
        return "schedule-validation/upload";
    }

    /**
     * Xử lý upload và validate file Excel
     */
    @PostMapping("/upload")
    public String processUpload(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        try {
            // Validate file
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Vui lòng chọn file Excel để upload");
                return "redirect:/schedule-validation";
            }

            if (!excelReaderService.validateScheduleExcelFormat(file)) {
                redirectAttributes.addFlashAttribute("error", 
                    "File không đúng định dạng thời khóa biểu. Vui lòng kiểm tra lại file Excel.");
                return "redirect:/schedule-validation";
            }

            // Read schedule data
            List<ScheduleEntry> scheduleEntries = excelReaderService.readScheduleFromExcel(file);
            
            if (scheduleEntries.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", 
                    "Không tìm thấy dữ liệu thời khóa biểu trong file. Vui lòng kiểm tra lại.");
                return "redirect:/schedule-validation";
            }

            // Detect conflicts
            ConflictResult conflictResult = conflictDetectionService.detectConflicts(scheduleEntries);

            // Add to model for results page
            model.addAttribute("conflictResult", conflictResult);
            model.addAttribute("scheduleEntries", scheduleEntries);
            model.addAttribute("fileName", file.getOriginalFilename());
            model.addAttribute("totalEntries", scheduleEntries.size());

            return "schedule-validation/results";

        } catch (Exception e) {
            log.error("Error processing schedule file", e);
            redirectAttributes.addFlashAttribute("error", 
                "Lỗi khi xử lý file: " + e.getMessage());
            return "redirect:/schedule-validation";
        }
    }

    /**
     * Trang kết quả validate 
     */
    @GetMapping("/results")
    public String resultsPage() {
        // Redirect to upload if accessed directly
        return "redirect:/schedule-validation";
    }

    /**
     * API endpoint để lấy chi tiết xung đột (cho AJAX nếu cần)
     */
    @GetMapping("/api/conflicts")
    @ResponseBody
    public ConflictResult getConflicts(@RequestParam("data") String base64Data) {
        // Implementation for AJAX calls if needed
        return ConflictResult.builder().build();
    }
}