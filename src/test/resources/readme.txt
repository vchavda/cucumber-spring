To run API:
mvn clean verify -Dcucumber.options="--tags @rest-ts001"  -Dspring.profiles.active=chrome (this is nothing to do with API but we have @profile
so code needs to inistilaze the bean

To run Selenium
mvn clean verify -Dcucumber.options="--tags @sel-ts001"  -Dspring.profiles.active=chrome  (for chrome browser)
mvn clean verify -Dcucumber.options="--tags @sel-ts001"  -Dspring.profiles.active=firefox  (for firefox browser)
mvn clean verify -Dcucumber.options="--tags @seleasy-ts001"  -Dspring.profiles.active=firefox

To Debug:
right-click the scenario you want ot debug
Then go to the configurtion screen
remove spring from glue just leave cukes.step
shorten command line to classpath file.

Add CroPath to your chrome browser to get the xpath for any element
https://chrome.google.com/webstore/detail/chropath/ljngjbnaijcbncmcnjfhigebomdlkcjo?hl=en
---------------------------------------------------------------------------------------------------------------------------------------

DTO: If you create a DTO and you have child objects
eg:

pulic class ExampleDTO {
    public Page page;
    public SearchCriteria searchCriteria;
 }

 Then when you instantiate ExampleDTO you will find that Page and SearchCriteria does not get instantiated. Best way is to
 get a template json and use Jackson to populate it.

 you can use the following statements:

 String defautDTO = fileReader.fileContentsFromClasspath("dtoTemplate.json");
 return new ObjectMapper().readValue(defaultDTO, ExampleDTO.class);

 fileReader is a class you need to create:

 public class FileReader {

    public FileReader() {
    }

    public String fileContentsFromClasspath (String location) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        return (new Scanner(resourceAsStream, "utf-8")).useDelimiter("\\Z").next();
    }

 }

 ---------------------------------------------------------------------------------------------------------------------------------------
 If you have a DTO which has an object eg:

 public class Identifiers {
    @JsonProperty("identifier")
    public String identifiers
    @JsonProperty("type")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public int type
 }

 If you want a json response like below then you need the JsonInclude so it does not add the node if you don't pass anything to
 it. otherwise by default it will be like "type": 0  added.

 So when you have created a DTO for this and populatating the object you can ommit the population of type and it will handle it and
 produce a JSON like below.

 OR you can just put the annotation above the class so it will apply to all of the member variables

 @JsonInclude(JsonInclude.Include.NON_DEFAULT)
 public class Identifiers {
     @JsonProperty("identifier")
     public String identifiers
     @JsonProperty("type")
     public int type
  }


 "identifier": [
    {
       "identifer": 1233,
       "type": 2323
    },
    {
        "identifer": 1233,
    }
]