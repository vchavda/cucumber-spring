package restAssured;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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


public class GetRequest {

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
        configuration.addOptions(Option.ALWAYS_RETURN_LIST);

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

       List<String> values = JsonPath.parse(response.getBody().asString()).read("$.data[*].name");

        assertThat(colors).containsExactlyInAnyOrder("green", "Blue", "red");
        assertThat(values).containsExactlyInAnyOrder("cerulean", "fuchsia rose","true red","aqua sky","tigerlily","blue turquoise");
        assertThat(values).doesNotContain("Invalid");
        assertThat(values).isNotEmpty();
        assertThat(values).doesNotHaveDuplicates().doesNotContainNull();
        assertThat(values).hasSize(6);
        assertThat(values).contains("cerulean");
        assertThat(response.getStatusCode()).isEqualTo(200);
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




}
