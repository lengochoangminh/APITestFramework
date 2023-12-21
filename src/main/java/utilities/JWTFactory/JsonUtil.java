package utilities.JWTFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.Optional;

public class JsonUtil {

    private JsonUtil() {
    }

    /**
     * Convert to json to Object
     *
     * @param data: json need to convert to object
     * @param clazz : POJO object which you want to convert from json string
     * @param <T>   : POJO class need to return
     * @return Instance of Class T or Optional.empty() in case cannot deserialization
     */
    public static <T> Optional<T> toObject(String data, Class<T> clazz) {
        if (StringUtils.isEmpty(data) || Objects.isNull(clazz)) {
            return Optional.empty();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        try {
            return Optional.of(objectMapper.readValue(data, clazz));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * From object to json
     *
     * @param data : object need to convert to json string
     * @return : json string
     */
    public static Optional<String> toJson(Object data) {
        if (Objects.isNull(data)) {
            return Optional.empty();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        try {
            return Optional.of(objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

}
