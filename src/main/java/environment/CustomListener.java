package environment;

import org.apache.log4j.Logger;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.restassured.RestAssured;

public class CustomListener implements ITestListener, IExecutionListener {

    private static final Logger LOGGER = Logger.getLogger(CustomListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void onExecutionStart() {
        restAssuredSetupInGlobal();
    }

    @Override
    public void onExecutionFinish() {
    }

    private void restAssuredSetupInGlobal() {
        RestAssured.reset();
        RestAssured.urlEncodingEnabled = false;
    }

}
