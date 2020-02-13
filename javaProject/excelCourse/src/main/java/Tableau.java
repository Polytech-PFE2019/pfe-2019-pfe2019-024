import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.UnsupportedFileFormatException;
import org.apache.poi.ss.usermodel.*;

public class Tableau
{
    private String header[];
    private Object body[][];
    private String lastFileName = null;
    private String lastSheetName = null;

    public Tableau(String fileName)
    {
        try
        {
            this.setLastFileName(fileName);
            FileInputStream file = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(file);
            final Sheet sheet = workbook.getSheetAt(0);
            int top = sheet.getFirstRowNum();
            int bottom = sheet.getLastRowNum();
            Row line = sheet.getRow(top);
            int start = line.getFirstCellNum();
            int end = line.getLastCellNum();
            int length = end - start;
            while(length == 0)
            {
                top++;
                line = sheet.getRow(top);
                start = line.getFirstCellNum();
                end = line.getLastCellNum();
                length = end - start;
            }
            int hight = bottom - top;
            this.header =  new String[length];
            this.body = new Object[hight][length];
            for (int i = 0; i < length; i++)
            {

                header[i] = line.getCell(start + i).getStringCellValue();
            }

            for (int index = 0; index < hight; index++)
            {
                line = sheet.getRow(index + top + 1);
                for (int i = 0; i < length; i++) {
                    Cell cellule = line.getCell(start + i);
                    if (cellule != null) {
                        switch (cellule.getCellType()) {
                            case STRING:
                                this.body[index][i] = cellule.getStringCellValue();
                                break;
                            case BOOLEAN:
                                this.body[index][i] = cellule.getBooleanCellValue();
                                break;
                            default:
                                this.body[index][i] = cellule.getNumericCellValue();
                        }
                    }else{
                        this.body[index][i] = null;
                    }
                }
            }
            workbook.close();
            file.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void saveAs(String fileName, String sheetName)
    {
        try
        {
            if (this.getLastFileName().compareTo(fileName) != 0)
                this.setLastFileName(fileName);
            if (this.getLastSheetName() != sheetName)
                this.setLastSheetName(sheetName);
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);
            Row row = sheet.createRow(0);
            for(int i = 0; i < this.getHeader().length; i++)
            {
                row.createCell(i).setCellValue(this.getHeader()[i]);
            }

            for (int index = 0; index < this.getBody().length; index++)
            {
                row = sheet.createRow(index + 1);
                for (int i = 0; i < this.getBody()[index].length; i++)
                {
                    String valeur = String.valueOf(this.getBody()[index][i]);
                    row.createCell(i).setCellValue(valeur);
                }
            }
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        this.saveAs(this.getLastFileName(), this.getLastSheetName());
    }

    public String[] getHeader()
    {
        return this.header;
    }

    public Object[][] getBody()
    {
        return this.body;
    }

    public String getLastFileName() {
        return this.lastFileName;
    }

    private void setLastFileName(String lastFileName) {
        this.lastFileName = lastFileName;
    }

    public String getLastSheetName() {
        return this.lastSheetName;
    }

    private void setLastSheetName(String lastSheetName) {
        this.lastSheetName = lastSheetName;
    }

    public String compareHeader(Tableau tab){
        ArrayList<Object> header = new ArrayList<Object>(Arrays.asList(this.header));
        int diff = getHeader().length;
        String res = "";
        for(int i = 0; i < tab.getHeader().length; i++){
            if(header.contains(tab.getHeader()[i])){
                res = res +tab.getHeader()[i];
                res += "\n";
                diff--;
            }
        }

        return res +"."+String.format("%.02f",(((float)(getHeader().length - diff)/getHeader().length)*100))+"%";
    }


}