package restAssuredUtils;

import org.springframework.stereotype.Component;

@Component
public class ResponseApi {

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    private Integer responseCode = 0;



}
