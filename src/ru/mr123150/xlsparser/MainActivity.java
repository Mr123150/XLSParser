package ru.mr123150.xlsparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText tv;

	private static final String TAG = "myLogs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv=(EditText)findViewById(R.id.tv);
		
		try {
			List<String> res = read("/sdcard/download/kib1.xls");
			String[] strres = res.toArray(new String[0]);
			tv.setText(Arrays.toString(strres));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public List<String> read(String fileName) throws IOException  {
	    List<String> resultSet = new ArrayList<String>();

	    //File inputWorkbook = new File(inputFile);
	    /*File inputWorkbook = new File("/sdcard/download/kib1.xls");
	    if(inputWorkbook.exists()){
	        Workbook w;
	        WorkbookSettings ws = new WorkbookSettings();
	        ws.setEncoding("UTF-16BE");
	        try {
	            w = Workbook.getWorkbook(inputWorkbook,ws);
	            // Get the first sheet
	            Sheet sheet = w.getSheet(0);
	            // Loop over column and lines
	            for (int j = 0; j < sheet.getRows(); j++) {
	                Cell cell = sheet.getCell(0, j);
	                if(cell.getContents().equalsIgnoreCase(key)){
	                    for (int i = 0; i < sheet.getColumns(); i++) {
	                        Cell cel = sheet.getCell(i, j);
	                        resultSet.add(cel.getContents());
	                    }
	                }
	                continue;
	            }
	        } catch (BiffException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    else
	    {
	        resultSet.add("File not found..!");
	    }
	    if(resultSet.size()==0){
	        resultSet.add("Data not found..!");
	    }
	    return resultSet;*/
	    FileInputStream fis = new FileInputStream(fileName);
        
        //Create Workbook instance for xlsx/xls file input stream
	    Workbook workbook = new HSSFWorkbook(fis);
        /*if(fileName.toLowerCase().endsWith("xlsx")){
            workbook = new XSSFWorkbook(fis);
        }else if(fileName.toLowerCase().endsWith("xls")){
            workbook = new HSSFWorkbook(fis);
        }*/
        
      //Get the nth sheet from the workbook
	    Sheet sheet = workbook.getSheetAt(0);
         
        //every sheet has rows, iterate over them
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext())
        {
             
            //Get the row object
        	Row row = rowIterator.next();
             
            //Every row has columns, get the column iterator and iterate over them
            Iterator<Cell> cellIterator = row.cellIterator();
              
            while (cellIterator.hasNext())
            {
                //Get the Cell object
            	Cell cell = cellIterator.next();
                resultSet.add(cell.getStringCellValue().trim());
            } //end of cell iterator
            //Country c = new Country(name, shortCode);
            
            
        } //end of rows iterator
            
         
        //close file input stream
        fis.close();
        
        return resultSet;
	}

}
