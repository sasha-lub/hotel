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
@EnableAspectJAutoProxy( proxyTargetClass = false )
public abstract  class BaseRootConfiguration implements SchedulingConfigurer {

    protected String getPersistenceUnitName (){
        return "HotelUpdate";
    };

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean ()
    {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform( "org.hibernate.dialect.PostgreSQLDialect" );

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter( adapter );
        factory.setPersistenceUnitName( getPersistenceUnitName() );
        return factory;
    }

    @Bean
    public DataSource dataSource() {
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://ec2-50-19-218-160.compute-1.amazonaws.com:5432/d97q0jv8cn7puo?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        ds.setUsername("xgnunjgoyqvvel");
        ds.setPassword("7391371271092fe05f59c1fa9affb2b68485f88b580493271d1e6df443533f3d");
        return ds;
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