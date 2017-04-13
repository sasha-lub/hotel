package configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan( basePackages = { "service.impl", "dao.impl", "aspects"})
@EnableTransactionManagement(
        mode = AdviceMode.PROXY, proxyTargetClass = false,
        order = Ordered.LOWEST_PRECEDENCE
)
@EnableAsync
@EnableAspectJAutoProxy( proxyTargetClass = false )
public abstract  class BaseRootConfiguration implements SchedulingConfigurer {

    protected String getPersistenceUnitName (){
        return "HotelUpdate";
    };

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean ()
    {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform( "org.hibernate.dialect.MySQL5Dialect" );

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( adapter );
        factory.setPersistenceUnitName( getPersistenceUnitName() );
        return factory;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager()
    {
        return new JpaTransactionManager(
                this.entityManagerFactoryBean().getObject()
        );
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean()
    {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setProviderClass( HibernateValidator.class );
        return validator;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor ()
    {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator( this.localValidatorFactoryBean() );
        return processor;
    }

    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setFallbackToSystemLocale( false );
        messageSource.setDefaultEncoding( StandardCharsets.UTF_8.name());
        messageSource.setBasenames(
                "/WEB-INF/i18n/resources"
        );
        return messageSource;
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler()
    {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
        scheduler.setAwaitTerminationSeconds(60);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    @Override
    public void configureTasks( ScheduledTaskRegistrar registrar )
    {
        TaskScheduler scheduler = this.taskScheduler();
        registrar.setTaskScheduler( scheduler );
    }
}