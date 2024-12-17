package com.trimble.trimbleCareLeaseProject.Controller;

import com.trimble.trimbleCareLeaseProject.DTO.LeaseDTO;
import com.trimble.trimbleCareLeaseProject.ServiceImpl.LeaseServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lease")
public class LeaseController {

    @Autowired
    private LeaseServiceImpl leaseService;
    
    @PostMapping("/start")
    public ResponseEntity<?> startLease(
        @RequestParam Long carId,
        @RequestParam Long userId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        try {
            LeaseDTO leaseDTO = leaseService.startLease(carId, userId, startDate, endDate);
            return ResponseEntity.ok(leaseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }


   

    // End a lease
    @PostMapping("/end")
    public LeaseDTO endLease(@RequestParam Long leaseId) {
        return leaseService.endLease(leaseId);
    }

    // Get lease history for a car in Excel format
    @GetMapping("/car/{carId}/excel")
    public ResponseEntity<byte[]> getCarLeaseHistoryExcel(@PathVariable Long carId) {
        List<LeaseDTO> leases = leaseService.getCarLeaseHistory(carId);
        byte[] excelData = generateExcelFile(leases, "Car Lease History");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=car_lease_history.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }

    // Get lease history for a customer in Excel format
    @GetMapping("/customer/{customerId}/excel")
    public ResponseEntity<byte[]> getCustomerLeaseHistoryExcel(@PathVariable Long customerId) {
        List<LeaseDTO> leases = leaseService.getCustomerLeaseHistory(customerId);
        byte[] excelData = generateExcelFile(leases, "Customer Lease History");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customer_lease_history.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }

    // Helper method to generate Excel file
    private byte[] generateExcelFile(List<LeaseDTO> leases, String sheetName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Lease ID", "Car ID", "Car Model", "Customer ID", "Customer Name", "Start Date", "End Date"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Populate rows with lease data
            int rowIndex = 1;
            for (LeaseDTO leaseDTO : leases) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(leaseDTO.getId());
                row.createCell(1).setCellValue(leaseDTO.getCarId());
                row.createCell(2).setCellValue(leaseDTO.getCarModel());
                row.createCell(3).setCellValue(leaseDTO.getCustomerId());
                row.createCell(4).setCellValue(leaseDTO.getCustomerName());
                row.createCell(5).setCellValue(leaseDTO.getStartDate().toString());
                row.createCell(6).setCellValue(leaseDTO.getEndDate().toString());
            }

            // Autosize columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to byte array
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while generating Excel file", e);
        }
    }

    // Helper method to create header cell style
    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
