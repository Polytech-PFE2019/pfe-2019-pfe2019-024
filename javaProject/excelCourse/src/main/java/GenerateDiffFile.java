import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.UnsupportedFileFormatException;
import org.apache.poi.ss.usermodel.*;

public class GenerateDiffFile {

    private HSSFWorkbook workbook;
    private File file;
    private CellStyle cs;


    public GenerateDiffFile(){
        workbook = new HSSFWorkbook();
        file = new File(System.getProperty("user.dir")+"\\excelCourse\\src\\main\\output\\diff.xls");
        cs = workbook.createCellStyle();
        cs.setWrapText(true);
    }

    public void generateHeaderDiffSheet(ArrayList<Tableau> tab, List<String> expName) throws IOException {

        HSSFSheet sheet = workbook.createSheet("Header");
        Cell cell;
        Row row;
        row = sheet.createRow(0);

        //generate line and colonne name in fuction of file
        for(int i = 0 ; i < tab.size();i++){
            cell = row.createCell(i+1, CellType.STRING);
            cell.setCellValue(expName.get(i).substring(0,expName.get(i).indexOf(".")));
        }

        for(int i = 0 ; i < tab.size();i++) {
            row = sheet.createRow(i+1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(expName.get(i).substring(0,expName.get(i).indexOf(".")));
            for(int j = 0 ; j < tab.size();j++){
                 if((sheet.getRow(i+1).getCell(0).getStringCellValue().contains("niveau_1")&&
                   sheet.getRow(0).getCell(j+1).getStringCellValue().contains("niveau_1"))||
                   (sheet.getRow(i+1).getCell(0).getStringCellValue().contains("niveau_2") &&
                    sheet.getRow(0).getCell(j+1).getStringCellValue().contains("niveau_2"))){

                    cell = row.createCell(j + 1, CellType.STRING);
                    if (i != j) {
                        cell.setCellValue(tab.get(i).compareHeader(tab.get(j)).substring(tab.get(i).compareHeader(tab.get(j)).indexOf(".") + 1));
                    } else {
                        cell.setCellValue("xxx");
                    }
                    cell.setCellStyle(cs);
                }
                else{
                    cell.setCellStyle(cs);
                }
            }
        }

        for(int i = 0 ; i < tab.size()+1;i++){
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);

    }

    public void generateHeaderSameNameFile(ArrayList<Tableau> tab, List<String> expName) throws IOException {

        HSSFSheet sheet = workbook.createSheet("HeaderNameSameColone");
        Cell cell;
        Row row;
        row = sheet.createRow(0);

        for(int i = 0 ; i < tab.size();i++){
            cell = row.createCell(i+1, CellType.STRING);
            cell.setCellValue(expName.get(i));
        }

        for(int i = 0 ; i < tab.size();i++) {
            row = sheet.createRow(i+1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(expName.get(i));
            for(int j = 0 ; j < tab.size();j++){

                cell = row.createCell(j+1, CellType.STRING);
                if(i != j) {
                    cell.setCellValue(tab.get(i).compareHeader(tab.get(j)).substring(0,tab.get(i).compareHeader(tab.get(j)).indexOf(".")));
                }else{
                    cell.setCellValue("xxx\nxxx\nxxx");
                }
                cell.setCellStyle(cs);
            }
        }

        for(int i = 0 ; i < tab.size()+1;i++){
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);

    }
}
