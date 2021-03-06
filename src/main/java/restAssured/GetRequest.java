package restAssured;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.split;
import static org.assertj.core.api.Assertions.assertThat;

/*{
        "page": 1,
        "per_page": 6,
        "total": 12,
        "total_pages": 2,
        "data": [
        {
        "id": 1,
        "name": "cerulean",
        "year": 2000,
        "color": "#98B2D1",
        "pantone_value": "15-4020"
        },
        {
        "id": 2,
        "name": "fuchsia rose",
        "year": 2001,
        "color": "#C74375",
        "pantone_value": "17-2031"
        },
        {
        "id": 3,
        "name": "true red",
        "year": 2002,
        "color": "#BF1932",
        "pantone_value": "19-1664"
        },
        {
        "id": 4,
        "name": "aqua sky",
        "year": 2003,
        "color": "#7BC4C4",
        "pantone_value": "14-4811"
        },
        {
        "id": 5,
        "name": "tigerlily",
        "year": 2004,
        "color": "#E2583E",
        "pantone_value": "17-1456"
        },
        {
        "id": 6,
        "name": "blue turquoise",
        "year": 2005,
        "color": "#53B0AE",
        "pantone_value": "15-5217"
        }
        ]
        }*/


public class GetRequest<function> {

    private Configuration configuration;


    @Test
    public void getStstusCode() {
        RestAssured.baseURI = "https://reqres.in/api/users/1";
         Response response = null;

        try {

            response = RestAssured.given()
                    .when()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response : " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        assertThat(200).isEqualTo(response.getStatusCode());
    }


    @Test
    public void getList() {

        Configuration configuration = Configuration.defaultConfiguration();
        this.configuration = Configuration.builder().options(new Option[]{Option.ALWAYS_RETURN_LIST}).build();
        //configuration.addOptions(Option.ALWAYS_RETURN_LIST);

        boolean boolTest  = false;


        RestAssured.baseURI = "https://reqres.in/api/unknown";
        Response response = null;

        try {

            response = RestAssured.given()
                    .when()
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("Blue");
        colors.add("green");

        System.out.println("Response : " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());



       // testing Mat Robinsions (this works well when you need to split the string using another deliminator eg  | instead of comma
        // for example if the items you seperating also has commas ie oxford,england, nairobi,kenya then you can define it as
        // oxford,england|nairobi,kenya and this would then split it correctly.
        //option one use this method
        String val = "cerulean,fuchsia rose,true red,aqua sky,tigerlily,blue turquoise";
        assertThat(this.checkIfValuesExistsInResponse(val,"data[*].name",response.getBody().asString())).isTrue();

        //option 2 use this and compare the string using assertJ
        List<String> values = JsonPath.parse(response.getBody().asString()).read("$.data[*].name");
        assertThat(colors).containsExactlyInAnyOrder("green", "Blue", "red");
        assertThat(values).containsExactlyInAnyOrder("cerulean", "fuchsia rose","true red","aqua sky","tigerlily","blue turquoise");

        assertThat(values).doesNotContain("Invalid");
        assertThat(values).isNotEmpty();
        assertThat(values).doesNotHaveDuplicates().doesNotContainNull();
        assertThat(values).hasSize(6);
        assertThat(values).contains("cerulean");
        assertThat(response.getStatusCode()).isEqualTo(200);
        // if we want a custom message if it fails then we can do this
        assertThat(response.getStatusCode()).withFailMessage("this is my fail message").isEqualTo(200);
        assertThat(values).isNotNull();
        assertThat(values).doesNotHaveDuplicates();
        assertThat(values.size()).isGreaterThan(1).isLessThan(10);
        assertThat(15).isBetween(10,20).isLessThan(20).isGreaterThan(10).isEqualTo(15);
        assertThat(boolTest).isFalse();
        assertThat(boolTest).isNotEqualTo(true);
        assertThat("someString").contains("some");
        assertThat("someStrinf").containsIgnoringCase("SOME");
        assertThat("someString").doesNotContain("POP");
        assertThat("someString").doesNotEndWith("Z").doesNotStartWith("A").isEqualTo("someString").isNotEqualTo("POP");


    }



    // this function takes a comma delimitted values that should be expected back in the json response (exaactly those values - no more or less).
    // you prvide the commma delimitted list and the node of the json that where to find the actual values
    // the comma dellimited list are placed in a list and so id the actual values
    // finaly they are converted to hashset to remove duplicate values and compared.
    //if you want to have a different delimitter instead of a comma incase the list itself has commas eg "nairobi,kenya"
    private boolean  checkIfValuesExistsInResponse(String valuesToCompare, String node, String responseBody) {
        List listOfValuesFromResponseBody =  (List) ((List)JsonPath.using(this.configuration).parse(responseBody).read("$."+node, new Predicate[0]));
        List<String> expectedValues = Arrays
                .stream(split(valuesToCompare, ",")).sorted().collect(Collectors.toList());
        return (new HashSet<>(expectedValues)).equals(new HashSet<>(listOfValuesFromResponseBody));
    }

}
