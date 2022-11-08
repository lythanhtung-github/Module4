package cg.wbd.grandemonstration.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class DuplicateEmailException extends Exception {
    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/customers/inputs-not-acceptable");
    }
}
