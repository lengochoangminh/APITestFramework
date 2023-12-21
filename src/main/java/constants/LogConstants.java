package constants;

public class LogConstants {

    //Environment configuration log
    public static final String ENVIRONMENT_INITIALIZATION_MESSAGE = "******** INITIALIZING TEST ENVIRONMENT [%s] ********";

    public static final String FAILED_TO_INITIAL_TEST_ENVIRONMENT = "Failed to initializing test dataHandler, please check your yaml config file!!!";

    public static final String TEST_ENVIRONMENT_DOES_NOT_EXISTS = "The test dataHandler [%s] doesn't exists. Please input the correct one! Error >>>> ";

    //Convert log
    public static final String CONVERT_FILE_PATH = "File path >>>> ";

    public static final String CONVERT_YML_FILE_TO_OBJECT = "Failed to convert yml file to object >>>> ";

    //Listener log
    public static final String THE_TEST_STATUS_LOG = "%s >>> %s";

    //Report log
    public static final String INITIALIZE_EXTENT_REPORT_MANAGER = "Initializing Extent report manager >>>> ";

    public static final String GENERATE_REPORT_FAILED = "Generate report failed!!! Please check your reporter again!!!";

    public static final String GENERATE_REPORT_COMPLETED = "Generate automation report completed!!!";

    //Base test log
    public static final String START_THE_TEST = "START THE TEST >>>> ";

    public static final String END_THE_TEST = "END THE TEST >>>> ";

    //Mock data log
    public static final String DESTROY_RESTITO_MOCK = "Destroying Restito Mock >>>>";

    public static final String FREE_RESTITO_MOCK = "Free all Restito Mock >>>>";

    public static final String START_RESTITO_MOCK_WITH_CONFIG = "Starting Restito Mock with configs >>>> ";

    public static final String HOST_NAME_AND_PORT = "Hostname and port: ";

    public static final String BASE_PATH = "Base path: ";

    public static final String DATA_SOURCE = "Data source: ";
}
