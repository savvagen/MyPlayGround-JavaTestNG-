package data;


import Utilities.ExelDataConfig;
import Utilities.ExelUtil;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginData {

    public ExelUtil exelUtil = new ExelUtil();


    @DataProvider(name = "invalidEmailData")
    public Object[][]invalidEmailData(){
        return new Object[][]{
                {"genchevskiy@@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"genchevski y@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"genchevskiqqwq@singree.com", "19021992qa", "Неправильный логин или пароль"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][]invalidLoginData(){
        return new Object[][]{
                {"Главная", "genchevskiy@@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"Главная", "genchevski y@singree.com", "19021992qa", "Неправильный логин или пароль"},
                {"Главная", "genchevskiqqwq@singree.com", "19021992qa", "Неправильный логин или пароль"}
        };
    }


    @DataProvider(name = "exelData1")
    public Object[][] parsDataFromExel() throws IOException {
        ExelDataConfig config = new ExelDataConfig("C:\\Users\\savva\\IdeaProjects\\TestNgProject\\src\\main\\java\\data\\loginData.xlsx");
        int rows = config.getRowCount(0);
        Object[][] data = new Object[rows][3];
        for (int i=0;i<rows;i++){
            data[i][0]=config.getData(0,i,0);
            data[i][1]=config.getData(0,i,1);
            data[i][2]=config.getData(0,i,2);

        }
        return data;
    }



    @DataProvider(name="exelData2")
    public Object[][] loginData() {
        Object[][] arrayObject = exelUtil.getExcelData("C:\\Users\\savva\\IdeaProjects\\TestNgProject\\src\\main\\java\\data\\loginData.xls","Sheet1");
        return arrayObject;
    }




    @DataProvider(name = "csvData")
    public Iterator<Object []> providerCsv() throws InterruptedException, IOException {
        String csvFile = "C:\\Users\\savva\\IdeaProjects\\TestNgProject\\src\\main\\java\\data\\loginData.csv";
        String cvsSplitBy = ",";
        List<Object []> testCases = new ArrayList<>();
        String[] data= null;
        //this loop is pseudo code
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        while ((line = br.readLine()) != null) {
            // use comma as separator
            data= line.split(cvsSplitBy);
            testCases.add(data);
        }
        return testCases.iterator();
    }


}




