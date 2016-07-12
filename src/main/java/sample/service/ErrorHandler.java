package sample.service;

/**
 * Created by markus on 12/07/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(path = "/error")
public class ErrorHandler {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> handle(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>((String) getErrorAttributes(request).get("message"), HttpStatus.valueOf(response.getStatus()));
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, false);
    }
}
