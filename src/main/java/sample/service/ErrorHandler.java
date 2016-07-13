package sample.service;

/**
 * Created by markus on 12/07/16.
 */

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(path = ERROR_PATH)
    public ResponseEntity<String> handle(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(status.getReasonPhrase(), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (IllegalArgumentException ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
