package shashiAutomation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {


    public static ExtentReports getReportObject()
    {
        String path =System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("TestNG selenium Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Shashikumar Madgi");
        return extent;



    }
}
