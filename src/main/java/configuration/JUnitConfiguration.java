package configuration;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Саня on 24.12.2016.
 */

@Configuration
public class JUnitConfiguration extends BaseRootConfiguration {

    @Override
    protected String getPersistenceUnitName() {
        return "HotelJUnit";
    }

}
