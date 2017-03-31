package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = "testapp" )
public class TestAppReportConfiguration extends BaseRootConfiguration {

    @Override
    protected String getPersistenceUnitName() {
        return "HotelRead";
    }
}