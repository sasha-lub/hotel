package aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Саня on 11.11.2016.
 */
@Component
@Aspect
@Order( 2 )
public class ServiceMethodLoggingAspect
{
    private static final Logger log = LogManager.getLogger();
    private static final Logger logErrors = LogManager.getLogger( "hotel-errors" );


    @Before( value = "@within(org.springframework.stereotype.Service) || @annotation(org.springframework.stereotype.Service)" )
    public void before ( JoinPoint joinPoint ) throws Throwable
    {
        log.debug( String.format(
                "START %s.%s\n",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName()
        ) );
    }

    @After( value = "@within(org.springframework.stereotype.Service) || @annotation(org.springframework.stereotype.Service)" )
    public void after( JoinPoint joinPoint ) throws Throwable
    {
        log.debug( String.format(
                "FINISH %s.%s\n",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName()
        ) );
    }

    @AfterThrowing( value = "@within(org.springframework.stereotype.Service) || @annotation(org.springframework.stereotype.Service)", throwing = "e" )
    public void handleException( JoinPoint joinPoint, Exception e ) throws Throwable {
        logErrors.catching( e );
    }
}