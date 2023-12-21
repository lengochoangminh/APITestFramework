package utilities.JWTFactory;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static String SECRET_KEY = "demo key=";
    private static long durationExpiredTime = 3600L;
    private static String issuer = "http://demo.io";
    private static String subject = "demo";
    private static String personId = "demo";
    private static String userId = "demo";

    public static String createJWT(String audience) {
        return createJWT(audience, null);
    }

    public static String createJWT(String audience, String user) {
        List<String> scopes = new ArrayList<String>();
        scopes.add("*:Parent:User");
        scopes.add("*:Edulog:Admin");

        if(user == null) {
            user = userId;
        }

        long now = Instant.now(Clock.systemUTC()).toEpochMilli();
        JWTSetting jwtSetting = new JWTSetting(issuer,
                subject,
                audience,
                personId,
                user,
                now,
                now + durationExpiredTime,
                scopes);

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setPayload(JsonUtil.toJson(jwtSetting).get())
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }
}
