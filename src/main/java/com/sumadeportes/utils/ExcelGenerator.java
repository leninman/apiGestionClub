package com.sumadeportes.utils;

import com.sumadeportes.model.dto.EventMarkDto;
import com.sumadeportes.model.dto.RegisterSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static byte[] generarExcel(RegisterSheet registerSheet) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Planilla");

            // Estilos
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle subHeaderStyle = createSubHeaderStyle(workbook);
            CellStyle borderedStyle = createBorderedStyle(workbook);
            borderedStyle.setAlignment(HorizontalAlignment.CENTER); // Centrado horizontal
            borderedStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Centrado vertical

            // Cabecera
            createLabelRow(sheet, 0, "COPA", registerSheet.getTournament(), headerStyle);
            createLabelRow(sheet, 1, "EQUIPO", registerSheet.getTeamName(), headerStyle);

            // Encabezado de tabla
            String[] headers = {"NADADOR Nº", "APELLIDO DEL ATLETA", "NOMBRE DEL ATLETA", "CATEGORÍA (Años)", "EVENTO", "TIEMPO DE INSCRIPCION", "EVENTO Nº"};
            Row headerRow = sheet.createRow(3);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(subHeaderStyle);
            }

            // Datos agrupados por nadador
            final int[] startRow = {4};
            registerSheet.getEventRegisterResponses().forEach(response -> {
                int groupStartRow = startRow[0];
                response.getEvents().forEach(event -> {
                    Row row = sheet.createRow(startRow[0]++);
                    row.createCell(0).setCellValue(response.getSwimmerNumber());
                    row.createCell(1).setCellValue(response.getSwimmerLastName());
                    row.createCell(2).setCellValue(response.getSwimmerName());
                    row.createCell(3).setCellValue(response.getSwimmerAge());
                    row.createCell(4).setCellValue(event.getEventName());
                    row.createCell(5).setCellValue(event.getMark());
                    row.createCell(6).setCellValue(event.getEventNumber());
                    for (int i = 0; i < 7; i++) {
                        row.getCell(i).setCellStyle(borderedStyle); // Aplicar estilo centrado
                    }
                });
                if (startRow[0] - groupStartRow > 1) {
                    for (int col = 0; col <= 3; col++) { // Columnas a centrar verticalmente
                        sheet.addMergedRegion(new CellRangeAddress(groupStartRow, startRow[0] - 1, col, col));
                    }
                }
            });

            // Ajuste automático del ancho de las columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
    // Métodos auxiliares de estilo

    private static CellStyle createHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    private static CellStyle createSubHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER); // Centrado horizontal
        style.setVerticalAlignment(VerticalAlignment.CENTER); // Centrado vertical
        setBorders(style);
        return style;
    }

    private static CellStyle createBorderedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        setBorders(style);
        return style;
    }

    private static void setBorders(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }

    private static void createLabelRow(Sheet sheet, int rowNum, String label, String value, CellStyle style) {
        Row row = sheet.createRow(rowNum);
        Cell cell1 = row.createCell(0);
        Cell cell2 = row.createCell(1);
        cell1.setCellValue(label);
        cell2.setCellValue(value);
        cell1.setCellStyle(style);
        cell2.setCellStyle(style);
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));
    }
}

