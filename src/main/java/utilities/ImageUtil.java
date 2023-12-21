package utilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class ImageUtil {
    private final static Logger logger = Logger.getLogger(ImageUtil.class);

    public static String getBase64StringFromImage(String filePath) {
        String encodedBase64image = null;
        try {
            File file = new File(filePath);
            byte[] bytes = FileUtils.readFileToByteArray(file);
            encodedBase64image = new String(Base64.encodeBase64(bytes));
        } catch (IOException e) {
            logger.error("Cannot convert image file:\n" + e);
        }

        return encodedBase64image;
    }

}
