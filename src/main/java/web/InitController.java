package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.constants.Path;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping(path = "/index")
public class InitController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRoot(HttpSession session, Map<String, java.lang.Object> model){

        return Path.PAGE_INDEX;
    }


}

