package restAssuredUtils;

import org.springframework.stereotype.Component;

@Component
public class ResponseApi {
    private Integer responseCode = 0;
    private String responseValue = "";

    private String requestValue = "";

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }


    public String getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }

    public String getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(String requestValue) {
        this.requestValue = requestValue;
    }


}
