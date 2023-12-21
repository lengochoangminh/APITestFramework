package enumerations;

public enum TestDataType {

    STAGING("src/main/resources/testdata/stage_component_test_data.json"),
    DEMO("src/main/resources/testdata/demo_component_test_data.json"),
    PRODUCTION("");

    private String value;

    TestDataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

