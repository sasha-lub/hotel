package configuration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import utils.searchfilter.DatesFilter;

/**
 * Created by Sasha on 30.03.2017.
 */
@Configuration
@ComponentScan( basePackages = "utils.searchfilter" )
public class FilterConfiguration extends BaseRootConfiguration{
    @Override
    protected String getPersistenceUnitName() {
        return "HotelRead";
    }

    @Bean
    protected ConfigurableApplicationContext initGenerateContext () {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        DatesFilter.class
                );
        return context;
    }
}
