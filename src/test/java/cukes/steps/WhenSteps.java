package cukes.steps;

import com.google.common.collect.Ordering;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import restAssuredUtils.CustomLogFilter;
import restAssuredUtils.Jsonutils;
import restAssuredUtils.ResponseApi;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;
import static org.apache.commons.lang3.StringUtils.split;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;


public class WhenSteps extends Base {

    private Logger log = LogManager.getLogger(WhenSteps.class);
    Configuration configuration;

    @Autowired
    Jsonutils jsonutils;

    @Autowired
    private ResponseApi respApi;

    @Autowired
    private CustomLogFilter customLogFilter;

    @Value("${user}")
    private String user;

    @Value("${baseUrl}")
    private String baseUrl;


    private RequestSpecification requestSpecification;


    @Before
    public void myBeforeStep() {

        // need this baseconfiguration for log4j to work properly
        BasicConfigurator.configure();
        this.logoutPertinentEnvironmentVariable();
        configuration = Configuration.builder().options(new Option[]{Option.ALWAYS_RETURN_LIST}).build();
    }

    ;

    //-----------------------------------------------------------------------------------------------------------------//
    private void logoutPertinentEnvironmentVariable() {
        this.log.info("user: " + this.user);
        this.log.debug("baseUrl: " + this.user);
    }


    @When("^I make a test call using this url: '(.*)'$")
    public void iMakeATestCallUsingThisUrlHttpsReqresInApiUnknown(String url) throws MalformedURLException {

        // reason for the substring her is that the baseUrl has single quotes so we need to remove it
        // otherwise you will get malformedurlexception no protocol exception
        RestAssured.baseURI = (url.isEmpty() ? baseUrl.substring(1, baseUrl.length() - 1) : url);
        Response response = null;
        try {

            response = given()
                    .when()
                    .filter(customLogFilter)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(response.getStatusCode()).isEqualTo(200);

        if (respApi != null) {
            System.out.println("NOT NULL");
            respApi.setResponseCode(response.getStatusCode());
            System.out.println("\n" + "APi Request" + customLogFilter.getRequestBuilderLogs());
            System.out.println("\n" + "APi Response" + customLogFilter.getResponseBuilderLogs());
            respApi.setRequestValue(customLogFilter.getRequestBuilderLogs().toString());
            respApi.setResponseValue(customLogFilter.getResponseBuilderLogs().toString());
            respApi.setResponseTime(response.getTime());

        } else {
            System.out.println("NULL !");
        }
    }


    @Given("^a user spcifies query parameters:$")
    public void aUserSpcifiesQueryParameters(Map<String, String> qp) {
        requestSpecification = given().filter(customLogFilter).queryParams(qp);
    }

    @Given("^I submit dynamic headers$")
    public void iSubmitDynamicHeaders(Map<String, String> hp) {
        requestSpecification = given().filter(customLogFilter).headers(hp);
    }

    @When("^I make a request to this url: '(.*)'$")
    public void iMakeARequestToThisUrlUrl(String endpoint) {
        Response response = requestSpecification.get(endpoint);

        if (respApi != null) {
            System.out.println("NOT NULL");
            respApi.setResponseCode(response.getStatusCode());
            System.out.println("\n" + "APi Request" + customLogFilter.getRequestBuilderLogs());
            System.out.println("\n" + "APi Response" + customLogFilter.getResponseBuilderLogs());
            respApi.setRequestValue(customLogFilter.getRequestBuilderLogs().toString());
            respApi.setResponseValue(customLogFilter.getResponseBuilderLogs().toString());
            respApi.setResponseTime(response.getTime());

        } else {
            System.out.println("NULL !");
        }
    }

    @When("^I make a post request to this url: '(.*)'$")
    public void iMakePostARequestToThisUrlUrl(String endpoint) {
        String myBody = null;
        // to replace something in the body before posting eg like create a unique id
        myBody.replaceAll("VC_TOKEN", java.util.UUID.randomUUID().toString());
        Response response = requestSpecification.body(myBody).post(endpoint);

    }


    @And("^the node: '(.*)' is sorted in '(.*)' order$")
    public void theNodeIsSortedInAscendingOrder(String node, String order) {
        List<String> responseValue = JsonPath.parse(customLogFilter.getLastResponse()).read("$." + node);
        if (order.equals("Decending")) {
            assertTrue(Ordering.natural().reverse().isOrdered(responseValue) & responseValue.size() > 1);
        } else {
            assertTrue(Ordering.natural().isOrdered(responseValue) & responseValue.size() > 1);
        }
    }


    @And("^the response should contain this node: '(.*)' and value: '(.*)'$")
    public void theResponseShouldContainThisNodeAndValue(String node, String value) {
        String body = customLogFilter.getLastResponse();
        assertThat(jsonutils.getValueForKey(body, node)).isEqualTo(value);
    }


    @And("^I receive an empty respone for '(.*)''$")
    public void iRecieveAnEmptyReponse(String node, String value) {
        String body = customLogFilter.getLastResponse();
        org.hamcrest.MatcherAssert.assertThat(jsonutils.getValueForKey(body,node), org.hamcrest.CoreMatchers.anyOf(is("[]"), is("{}")));

    }

    @And("^the header: '(.*)' is populated$")
    public void theHeaderIsPopulated(String header) {
        Headers headers = customLogFilter.getHeaders();
        assertThat(StringUtils.isNotEmpty(headers.get(header).getValue()));
    }

    @And("^the response should only contain these values: '(.*)' for node: '(.*)'$")
    public void theResponseShpuldOnlyContainTheseValues(String values, String node) {
        Configuration configuration;
        configuration = Configuration.builder().options(new Option[]{Option.ALWAYS_RETURN_LIST}).build();
        List listOfValuesFromResponseBody = ((List) JsonPath.using(configuration).parse(customLogFilter.getLastResponse()).read("$." + node, new Predicate[0]));
        List<String> expectedValues = Arrays
                .stream(split(values, "|")).sorted().collect(Collectors.toList());
        assertThat(new HashSet<>(expectedValues)).isEqualTo(new HashSet<>(listOfValuesFromResponseBody));
    }


    @And("^the response should contain at least one of these values: '(.*)' for node: '(.*)'$")
    public void theResponseShpuldOnlyContainAtLeasetOneOfTheseValues(String values, String node) {
        boolean status = false;
        Configuration configuration;
        configuration = Configuration.builder().options(new Option[]{Option.ALWAYS_RETURN_LIST}).build();
        List listOfValuesFromResponseBody = ((List) JsonPath.using(configuration).parse(customLogFilter.getLastResponse()).read("$." + node, new Predicate[0]));
        List<String> expectedValues = Arrays
                .stream(split(values, "|")).sorted().collect(Collectors.toList());

        HashSet actualValues = new HashSet(listOfValuesFromResponseBody);
        actualValues.contains(expectedValues.get(0));
        for (String expVal: expectedValues) {
            if (actualValues.contains(expVal)) {
                status = true;
                break;
            }
        }
        assertTrue(status);
    }

    @And("^the response should not contain at least one of these values: '(.*)' for node: '(.*)'$")
    public void theResponseShpuldNotContainAtLeasetOneOfTheseValues(String values, String node) {
        boolean status = false;
        Configuration configuration;
        configuration = Configuration.builder().options(new Option[]{Option.ALWAYS_RETURN_LIST}).build();
        List listOfValuesFromResponseBody = ((List) JsonPath.using(configuration).parse(customLogFilter.getLastResponse()).read("$." + node, new Predicate[0]));
        List<String> expectedValues = Arrays
                .stream(split(values, "|")).sorted().collect(Collectors.toList());

        HashSet actualValues = new HashSet(listOfValuesFromResponseBody);
        actualValues.contains(expectedValues.get(0));
        for (String expVal: expectedValues) {
            if (actualValues.contains(expVal)) {
                status = true;
                break;
            }
        }
        assertFalse(status);
    }



    @And("^the number of of occurance of the node: '(.*)' in the response should be: '(\\d+)'$")
    public void theNumberOfOccuranceOfTheNode(String node, int hits) {
        List listOfValuesFromResponseBody = ((List) JsonPath.using(configuration).parse(customLogFilter.getLastResponse()).read("$." + node, new Predicate[0]));
        //option1
        assertThat(listOfValuesFromResponseBody.size()).isEqualTo(hits);
        //option2
        assertThat(jsonutils.numberOfoccuranceOfNode(customLogFilter.getLastResponse(), node)).isEqualTo(hits);
    }

    @And("^this node: '(.*)' should be returned in the response$")
    public void thisNodeShouldBeReturnedInTheResponse(String node) throws IOException {
        List li = ((List) JsonPath.using(configuration).parse(customLogFilter.getLastResponse()).read("$." + node));
        assertThat(li.size() > 1);
    }


    @And("^this static json test: '(.*)'$")
    public void staticJsonTest(String node) throws IOException {
        File file = (new File("src/test/resources/sampleJson.json"));
        String sampleResponse = org.apache.commons.io.FileUtils.readFileToString(file);
        String valx = JsonPath.read(sampleResponse,"$." + node, new Predicate[0]).toString();
        System.out.println("STATICX " + valx);
    }

    // not tested but essentiall removes the header
    @And("^this header is removed: '(.*)'$")
    public void thisHeaderIsRemoved(String key) throws IOException {
        List<Header> newCollection = new ArrayList<>();
        newCollection.addAll(customLogFilter.getHeaders().asList());
        List collect = (List)customLogFilter.getHeaders().asList().stream().filter((header) -> {
            return header.getName().equals(key);
        }).collect(Collectors.toList());
        newCollection.removeAll(collect);
        customLogFilter.setHeaders(new Headers(newCollection));
    }

    // this step is to demo that how we can check string with doule quotes in the example
    @And("^my respone should contain this '(.*)' and '(.*)'$")
    public void myResponeShouldContainThisNodeAndResponse(String val1, String val2) {
        String exp = val1.substring(1,val1.length()-1);
        Assert.assertEquals(exp, "red hat");
    }





//    @And("^the response should contain values$")
//    public void thisNodeShouldBeReturnedInTheResponsex(Map<String, String> table) {
//        for (String key : table.keySet()) {
//            MatcherAssert.assertThat(jsonutils.getValueForKey(customLogFilter.getLastResponse(), key), Is.is(table.get(key)));
//        }
//    }

}
//-----------------------------------------------------------------------------------------------------------------//

