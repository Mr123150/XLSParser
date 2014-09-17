package ru.mr123150.xlsparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
			List<String> res = read("");
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
	
	public List<String> read(String key) throws IOException  {
	    List<String> resultSet = new ArrayList<String>();

	    //File inputWorkbook = new File(inputFile);
	    File inputWorkbook = new File("/sdcard/download/kib1.xls");
	    if(inputWorkbook.exists()){
	        Workbook w;
	        WorkbookSettings ws = new WorkbookSettings();
	        ws.setEncoding("utf-16le");
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
	    return resultSet;
	}

}
