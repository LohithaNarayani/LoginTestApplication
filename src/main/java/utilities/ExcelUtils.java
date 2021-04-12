package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public String getLoginData(String columnName, int rowNumber) throws IOException {
		String cellValue = null;

		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\LoginDataSheet.xlsx");
		XSSFWorkbook workbook = null;

		try {
			workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("Dev");

			XSSFRow row = sheet.getRow(0);
			int col_num = -1;

			for(int i=0; i < row.getLastCellNum(); i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equals(columnName))
					col_num = i;
			}

			row = sheet.getRow(rowNumber);
			XSSFCell cell = row.getCell(col_num);

			cellValue = cell.getStringCellValue();
			System.out.println("Value of the Excel Cell is - "+ cellValue);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		finally {

			workbook.close();
		}

		return cellValue;
	}
	
	public ArrayList<String> getLoginData(int rowNumber) throws IOException {
		ArrayList<String> rowData = null;
		
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\LoginDataSheet.xlsx");
		XSSFWorkbook workbook = null;

		try {
			workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("Dev");
			
			//Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                	Cell cell =  (Cell) cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                System.out.println("");
            }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		finally {

			workbook.close();
		}
		return rowData;
	}
public ArrayList<String> getLoginData(String sheetName, String colName) throws IOException {
		
		//Method to read data from excel by taking sheetName and Column number

	FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\LoginDataSheet.xlsx");
		int colNo = -1;

		XSSFWorkbook book=new XSSFWorkbook(fs);

		XSSFSheet sheet=book.getSheet(sheetName);

		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();

		ArrayList<String> arraydata=new ArrayList<String>();
		
		XSSFRow row=sheet.getRow(0);

		for(int j=0;j<row.getLastCellNum();j++) {
			if(row.getCell(j).getStringCellValue().trim().equals(colName.trim()))
				colNo=j;
		}

		for(int i=1; i<=rowCount; i++) {
			//System.out.println(i);

			row=sheet.getRow(i);
	
			String data=row.getCell(colNo).getStringCellValue();

			//System.out.println(data);

			arraydata.add(data);
		}
		return arraydata;
	}
	
}
	

