package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import constants.LogConstants;
import exceptions.AutomationUtilException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConvertUtil {

    private ConvertUtil() {
    }

    private static final Logger LOGGER = Logger.getLogger(ConvertUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertYmlFileToObject(final String filePath, final Class<T> clazz) {
        LOGGER.info(LogConstants.CONVERT_FILE_PATH + filePath + "\n");
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            throw new AutomationUtilException(LogConstants.CONVERT_YML_FILE_TO_OBJECT, e);
        }
    }

    public static <T> T convertJsonFileToObject(String filePath, Class<T> clazz) {
        LOGGER.info("File path >>>> " + filePath + "\n");

        try {
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException var3) {
            throw new AutomationUtilException("Failed to convert json file to object >>>> ", var3);
        }
    }

    public static <T> T convertMapToObject(Map<Object, Object> map, final Class<T> clazz) {

        return mapper.convertValue(map,clazz);
    }

    public static String convertMapToJsonString(Map<Object, Object> map) throws JsonProcessingException {
        return mapper.writeValueAsString(map);
    }
}
