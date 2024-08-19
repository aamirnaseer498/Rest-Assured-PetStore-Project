package utilities;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "Data")
    public String[][] getAllData(){

        String path= System.getProperty("user.dir") + "//testdata//UsersData.xlsx";
        ExcelHelper excelHelper= new ExcelHelper(path);

        int rowNumber= excelHelper.getRowCount("Sheet1");
        int columnCount= excelHelper.getCellCount("Sheet1",1);

        String[][] apiData= new String[rowNumber][columnCount];

        for (int i=1; i<=rowNumber; i++){
            for (int j=0; j<columnCount; j++) {
                apiData[i-1][j]= excelHelper.getCellData("Sheet1",i,j);
            }
        }

        return apiData;

    }

    @org.testng.annotations.DataProvider(name = "UserNames")
    public String[] getUserNames(){

        String path= System.getProperty("user.dir") + "//testdata//UsersData.xlsx";
        ExcelHelper excelHelper= new ExcelHelper(path);

        int rowNumber= excelHelper.getRowCount("Sheet1");

        String[] apiData= new String[rowNumber];

        for (int i=1; i<=rowNumber; i++){
                apiData[i-1]= excelHelper.getCellData("Sheet1",i,1);
        }

        return apiData;

    }

}
