package configuration;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import testapp.ReportHandler;

import java.util.Locale;

/**
 * Created by Саня on 03.12.2016.
 */

@Configuration
@EnableWebMvc
@ComponentScan( basePackages = {"web"} )
public class WebConfiguration extends WebMvcConfigurerAdapter{
    @Bean
    public ViewResolver viewResolver ()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass( JstlView.class );
        resolver.setPrefix( "/WEB-INF/jsp/view/" );
        resolver.setSuffix( ".jsp" );
        return resolver;
    }

    @Bean
    public RequestToViewNameTranslator viewNameTranslator ()
    {
        return new DefaultRequestToViewNameTranslator();
    }

    @Override
    public void addInterceptors( InterceptorRegistry registry )
    {
        super.addInterceptors( registry );

        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName( "language" );

        registry.addInterceptor( interceptor );
    }

    @Bean
    public LocaleResolver localeResolver ()
    {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale( Locale.ENGLISH );
        return resolver;
    }
}
