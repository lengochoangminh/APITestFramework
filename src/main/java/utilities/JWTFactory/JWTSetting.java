package utilities.JWTFactory;

import java.util.List;

public class JWTSetting {
    private String iss;
    private String sub;
    private String personId;
    private String userId;
    private Long iat;
    private Long exp;
    private String aud;
    private List<String> scope;

    public JWTSetting(String iss, String sub, String audience, String personId, String userId, Long iat, Long exp, List<String> scope) {
        super();
        this.iss = iss;
        this.sub = sub;
        this.aud = audience;
        this.personId = personId;
        this.userId = userId;
        this.iat = iat;
        this.exp = exp;
        this.scope = scope;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }
}
