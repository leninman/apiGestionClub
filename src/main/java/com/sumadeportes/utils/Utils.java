package com.sumadeportes.utils;

import com.sumadeportes.model.dto.EventMarkDto;
import com.sumadeportes.model.dto.EventRegisterResponse;
import com.sumadeportes.model.dto.RegisterSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

public class Utils {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
    private static final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomPassword(int length) {
        if (length < 1) throw new IllegalArgumentException("Password length must be at least 1");

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(index));
        }
        return password.toString();
    }



    public  byte[] generateExcelEventsRegister(RegisterSheet registerSheet) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Registro de Eventos");
          //  Row headerRow0 = sheet.createRow(2);
           // String[] headers0 = {"PLANILLA DE INSCRICIÓN"};

          //  Row headerRow1 = sheet.createRow(2);
         //   String[] headers1 = {"COPA", registerSheet.getTournament()};

          //  Row headerRow2 = sheet.createRow(3);
          //  String[] headers2 = {"EQUIPO", registerSheet.getTeamName()};

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Tipo Documento", "Número Documento", "Nombre", "Apellido", "Género", "Edad", "Número Nadador", "Equipo", "Evento", "Marca"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // Llenar datos
            int rowNum = 1;
            for (EventRegisterResponse response : registerSheet.getEventRegisterResponses()) {
                for (EventMarkDto event : response.getEvents()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(response.getSwimmerDocType());
                    row.createCell(1).setCellValue(response.getSwimmerDocNumber());
                    row.createCell(2).setCellValue(response.getSwimmerName());
                    row.createCell(3).setCellValue(response.getSwimmerLastName());
                    row.createCell(4).setCellValue(response.getSwimmerGender());
                    row.createCell(5).setCellValue(response.getSwimmerAge());
                    row.createCell(6).setCellValue(response.getSwimmerNumber());
                    row.createCell(7).setCellValue(registerSheet.getTeamName());
                    row.createCell(8).setCellValue(event.getEventName());
                    row.createCell(9).setCellValue(event.getMark());
                }
            }

            // Ajustar columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Escribir el archivo en un arreglo de bytes
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}
