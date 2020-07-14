package restAssuredUtils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.springframework.stereotype.Component;

@Component
public class CustomLogFilter implements Filter {
    private StringBuilder requestBuilderLogs;
    private StringBuilder responseBuilderLogs;

    public CustomLogFilter() {

    }

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        Response response = filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
        requestBuilderLogs = new StringBuilder();
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Request method: " + (filterableRequestSpecification.getMethod()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Request URI: " + (filterableRequestSpecification.getURI()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Form Params: " + (filterableRequestSpecification.getFormParams()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Request Param: " + (filterableRequestSpecification.getRequestParams()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Headers: " + (filterableRequestSpecification.getHeaders()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Cookies: " + (filterableRequestSpecification.getCookies()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Proxy: " + (filterableRequestSpecification.getProxySpecification()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("Body: " + (filterableRequestSpecification.getBody()));
        requestBuilderLogs.append("\n");
        requestBuilderLogs.append("******************************");
        responseBuilderLogs = new StringBuilder();
        responseBuilderLogs.append("\n"+"\n"+"\n");
        responseBuilderLogs.append("Status Code: "+response.getStatusCode());
        responseBuilderLogs.append("\n");
        responseBuilderLogs.append("Status Line: "+response.getStatusLine());
        responseBuilderLogs.append("\n");
        responseBuilderLogs.append("Response Cookies: "+response.getDetailedCookies());
        responseBuilderLogs.append("\n");
        responseBuilderLogs.append("Response Content Type: "+response.getContentType());
        responseBuilderLogs.append("\n");
        responseBuilderLogs.append("Response Headers: "+response.getHeaders());
        responseBuilderLogs.append("\n");
        responseBuilderLogs.append("Response Body: "+"\n"+response.getBody().prettyPrint());
        return response;
    }

    public StringBuilder getRequestBuilderLogs() {
        return requestBuilderLogs;
    }

    public void setRequestBuilderLogs(StringBuilder requestBuilderLogs) {
        this.requestBuilderLogs = requestBuilderLogs;
    }

    public StringBuilder getResponseBuilderLogs() {
        return responseBuilderLogs;
    }

    public void setResponseBuilderLogs(StringBuilder responseBuilderLogs) {
        this.responseBuilderLogs = responseBuilderLogs;
    }



}
