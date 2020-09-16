package com.ffu.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ScrappingErrorException extends AbstractThrowableProblem {
    public ScrappingErrorException(String message){
        super(ErrorConstants.SCRAPPING_ERROR,message, Status.INTERNAL_SERVER_ERROR);
    }

}
