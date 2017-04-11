package aspects;

import exception.AppException;
import exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Created by Саня on 11.11.2016.
 */
@Component
@Aspect
@Order( 1 )
public class ExceptionHandlingAspect
{
    @Around( value = "@within(org.springframework.stereotype.Service) || @annotation(org.springframework.stereotype.Service)" )
    public Object interceptCall ( ProceedingJoinPoint joinPoint ) throws Throwable
    {
        try
        {
            return joinPoint.proceed();
        }
        catch ( ServiceException e )
        {
            throw e;
        }
        catch ( ConstraintViolationException e )
        {
            StringBuilder builder = new StringBuilder();
            builder.append( "Data validation error\n" );

            for ( ConstraintViolation< ? > violation : e.getConstraintViolations() )
            {
                builder.append( violation );
                builder.append( "\n" );
            }

            throw new ServiceException(e.getMessage());
        }
        catch ( Exception e )
        {
            throw new AppException();
        }
    }
}