package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateReport extends TestListenerAdapter {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest test;
    String reportName;

    @Override
    public void onStart(ITestContext testContext) {

        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName= "Test-Report-" + timeStamp + ".html";

        sparkReporter= new ExtentSparkReporter(".\\reports\\" + reportName);

        sparkReporter.config().setDocumentTitle("REST Assured Automation Framework");
        sparkReporter.config().setReportName(reportName);
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Application", "PET STORE USER API");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("user", "Aamir");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){

        test= extentReports.createTest(iTestResult.getName());
        test.assignCategory(iTestResult.getMethod().getGroups());
        test.createNode(iTestResult.getName());
        test.log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult iTestResult){

        test= extentReports.createTest(iTestResult.getName());
        test.assignCategory(iTestResult.getMethod().getGroups());
        test.createNode(iTestResult.getName());
        test.log(Status.FAIL, "Test Failed");

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){

        test= extentReports.createTest(iTestResult.getName());
        test.assignCategory(iTestResult.getMethod().getGroups());
        test.createNode(iTestResult.getName());
        test.log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onFinish(ITestContext testContext){

        extentReports.flush();

    }

}
