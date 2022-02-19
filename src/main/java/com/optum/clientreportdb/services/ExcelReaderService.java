package com.optum.clientreportdb.services;

import com.optum.clientreportdb.model.PolicyModel;
import com.optum.clientreportdb.repositories.PolicyJDBCRepository;
import com.optum.clientreportdb.repositories.PolicyRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class ExcelReaderService {

    @Autowired
    PolicyRepository policyRepository;

    public ExcelReaderService() {

    }

    public String readExcel(MultipartFile excel) {

        try {

            System.out.println("Starting......");

            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int i=1; i<sheet.getPhysicalNumberOfRows();i++) {
                XSSFRow row = sheet.getRow(i);
                String clientName = row.getCell(0).toString();
                String policyNumber = row.getCell(1).toString();
                String lprNumber = row.getCell(2).toString();

                /*System.out.println("Client Name : "+clientName);
                System.out.println("policyNumber : "+policyNumber);
                System.out.println("lprNumber: "+lprNumber);*/

                if (policyNumber != null && policyNumber.isEmpty()) {

                    System.out.println("Policy Number is either NULL or Empty  ROW# "+i);

                } else {
                    boolean isPolicyExists = isPolicyExists(policyNumber);
                    if (isPolicyExists) {
                        int status = updatePolicyDetails(clientName, lprNumber,policyNumber);
                        System.out.println("Updating Status..."+status);
                    } else {
                        int status = insertPolicyDetails(clientName, lprNumber, policyNumber);
                        System.out.println("Inserting Status..."+status);

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Success";
    }

    private boolean isPolicyExists(String policyNumber) {

        policyRepository = new PolicyJDBCRepository();
        PolicyModel policyModel = policyRepository.findByPolicyNumber(policyNumber);

        if ( Objects.isNull(policyModel)) {
            System.out.println("Policy Number NOT FOUND %%%%"+policyNumber);
            return false;
        }
        System.out.println("Policy Number EXISTS... ^^^"+policyNumber);
        return true;
    }

    private int updatePolicyDetails(String clientName, String lprNumber, String policyNumber) {

        int status = -1;

        try {

            PolicyModel policyModel = new PolicyModel();
            policyModel.setClient(clientName);
            policyModel.setLprNumber(lprNumber);
            policyModel.setPolicyNumber(policyNumber);
            policyModel.setIsActive(1);
            status = policyRepository.update(policyModel);

        } catch (Exception e) {

            System.out.println("Exception inside insertPolicyDetails::"+e.getMessage());
            e.printStackTrace();

        }

        return status;
    }

    private int insertPolicyDetails(String clientName, String lprNumber, String policyNumber) {

        int status = -1;

        try {

            PolicyModel policyModel = new PolicyModel();
            policyModel.setId(4);
            policyModel.setClient(clientName);
            policyModel.setLprNumber(lprNumber);
            policyModel.setPolicyNumber(policyNumber);
            Calendar cal = Calendar.getInstance();
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String year1 = year+"-01-01";
            policyModel.setStartDate(cal.getTime());
            policyModel.setEndDate(cal.getTime());
            policyModel.setIsActive(1);

            status = policyRepository.save(policyModel);

        } catch (Exception e) {

            System.out.println("Exception inside insertPolicyDetails::"+e.getMessage());
            e.printStackTrace();

        }

        return status;
    }

}
