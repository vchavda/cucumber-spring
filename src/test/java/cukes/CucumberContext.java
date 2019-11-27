package cukes;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"restAssuredUtils"})
@PropertySource(value = "classpath:applications.properties")
@Configuration
public class CucumberContext {


}
