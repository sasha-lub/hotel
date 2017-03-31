package testapp;

import configuration.TestAppGenerateConfiguration;
import configuration.TestAppReportConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Sasha on 19.02.2017.
 */
public class Demo {
    public static void main ( String[] args ) {
        try {
//            try (ConfigurableApplicationContext generateContext = initGenerateContext()){
//                GenerateHandler generateHandler = generateContext.getBean( GenerateHandler.class );
//                generateHandler.run();
//            }

            try (ConfigurableApplicationContext reportContext = initReportContext()){
                ReportHandler reportHandler = reportContext.getBean(ReportHandler.class);
                reportHandler.run();
            }


        }catch (Exception e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }


    private static ConfigurableApplicationContext initGenerateContext () {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        TestAppGenerateConfiguration.class
                );

        return context;
    }


    private static ConfigurableApplicationContext initReportContext () {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        TestAppReportConfiguration.class
                );

        return context;
    }
}
