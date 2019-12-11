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

    HSSFWorkbook workbook;
    File file;
    CellStyle cs;
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
                cell.setCellValue(tab.get(i).compareHeader(tab.get(j)));
                cell.setCellStyle(cs);
            }
        }
        for(int i = 0 ; i < tab.size();i++){
            sheet.autoSizeColumn(i);
        }
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);

    }
}
