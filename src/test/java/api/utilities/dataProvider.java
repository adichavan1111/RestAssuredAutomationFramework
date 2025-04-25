package api.utilities;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	@DataProvider(name="AllData")
	public String [][] allDataProvider()
	{
		String fname = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		
		int ttRowCnt = ReadExcelFile.getRowCount(fname, "sheet1");
		int ttColCnt = ReadExcelFile.getColCount(fname, "sheet1");
		
		String userData[][] = new String[ttRowCnt-1][ttColCnt] ;
		
		for(int rowno = 1;rowno<ttRowCnt;rowno++)
		{
			for(int colno =0;colno<ttColCnt;colno++)
			{
				userData[rowno-1][colno] = ReadExcelFile.getCellValue(fname, "sheet1", rowno, colno);
			}
		}
		return userData;
	}
	
	@DataProvider(name="userNameData")
	public String [] userDataProvider()
	{
		String fname = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
		int ttRowCnt = ReadExcelFile.getRowCount(fname, "sheet1");
		//int ttColCnt = ReadExcelFile.getRowCount(fname, "sheet1");
		
		String userNameData[]= new String[ttRowCnt-1];
		
		for(int rowno = 1;rowno<ttRowCnt;rowno++)
		{
			userNameData[rowno-1] = ReadExcelFile.getCellValue(fname, "sheet1", rowno, 1);
			
		}
		return userNameData;
	}
	

}
