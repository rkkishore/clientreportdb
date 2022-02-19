package com.optum.clientreportdb.contoller;

import com.optum.clientreportdb.services.ExcelReaderService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ReadExcelController {

    @PostMapping("/import")
    public String excelReader(@RequestParam("file") MultipartFile excel) {

        ExcelReaderService excelReaderService = new ExcelReaderService();

        return excelReaderService.readExcel(excel);
    }

}
