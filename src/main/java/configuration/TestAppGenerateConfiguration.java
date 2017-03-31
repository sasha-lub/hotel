package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Саня on 10.11.2016.
 */
@Configuration
@ComponentScan( basePackages = "testapp" )
public class TestAppGenerateConfiguration extends BaseRootConfiguration {

    @Override
    protected String getPersistenceUnitName() {
        return "HotelCreate";
    }
}