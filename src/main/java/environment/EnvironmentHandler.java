package environment;

import constants.LogConstants;
import enumerations.EnvironmentType;
import enumerations.TestDataType;
import exceptions.AutomationTestRunException;
import exceptions.AutomationUtilException;
import model.GenericModel;
import org.apache.log4j.Logger;
import utilities.ConvertUtil;

import java.util.Map;

public class EnvironmentHandler {
    private static final Logger LOGGER = Logger.getLogger(EnvironmentHandler.class);
    private static final String ENVIRONMENT_PARAM = "environment";
    private static EnvironmentHandler instance;

    private static GenericModel environmentModel;
    private static GenericModel testDataGenericModel;

    public EnvironmentType environment;

    private EnvironmentHandler(EnvironmentType environmentType) {
        String currentEnvParam = System.getProperty(ENVIRONMENT_PARAM);
        TestDataType testData;

        if (currentEnvParam != null) {
            environment = convertEnvironmentStringToEnvironmentType(currentEnvParam);
            testData = convertTestDataStringToTestDataType((currentEnvParam));
        } else {
            if (environmentType != null) {
                environment = environmentType;
                testData = convertTestDataStringToTestDataType(environmentType.name());
            } else {
                //Default environment is STAGING
                environment = EnvironmentType.STAGING;
                testData = TestDataType.STAGING;
            }
        }
        LOGGER.info(String.format(LogConstants.ENVIRONMENT_INITIALIZATION_MESSAGE, environment.toString()));

        try {
            environmentModel = ConvertUtil.convertYmlFileToObject(environment.getUrl(), GenericModel.class);
            testDataGenericModel = ConvertUtil.convertJsonFileToObject(testData.getValue(), GenericModel.class);
        } catch (AutomationUtilException e) {
            throw new AutomationTestRunException(LogConstants.FAILED_TO_INITIAL_TEST_ENVIRONMENT, e);
        }
    }

    public static EnvironmentHandler getInstance(EnvironmentType environmentType) {
        if (instance == null) {
            instance = new EnvironmentHandler(environmentType);
        }
        return instance;
    }

    public static EnvironmentHandler getInstance() {
        if (instance == null) {
            instance = new EnvironmentHandler(null);
        }
        return instance;
    }

    public GenericModel getEnvironment() {
        return environmentModel;
    }

    private EnvironmentType convertEnvironmentStringToEnvironmentType(String s) {
        EnvironmentType environmentType = EnvironmentType.STAGING;
        try {
            environmentType = EnvironmentType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOGGER.error(String.format(LogConstants.TEST_ENVIRONMENT_DOES_NOT_EXISTS, s) + e);
        }
        return environmentType;
    }

    private TestDataType convertTestDataStringToTestDataType(String s) {
        TestDataType testDataType = TestDataType.STAGING;
        try {
            testDataType = TestDataType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOGGER.error(String.format("\"The test data [%s] doesn't exists. Please input the correct one! Error >>>> \"", s) + e);
        }
        return testDataType;
    }

    public String getEnvironmentData(String key) {
        return environmentModel.getProperties().get(key).toString();
    }

    public String getTestData(String component, String key) {
        Map<String, Object> data = (Map<String, Object>) testDataGenericModel.getProperties().get(component);
        return data.get(key).toString();
    }

    public Map<String, Object> getTestDataToMap(String component) {
        Map<String, Object> data = (Map<String, Object>) testDataGenericModel.getProperties().get(component);
        return data;
    }
}
