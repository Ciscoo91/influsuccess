package com.ffu.service.errors;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ScrappingErrorException extends AbstractThrowableProblem {
    
    public static final URI SCRAPPING_ERROR = URI.create("https://www.jhipster.tech/problem/scrapping-error") ;

    public ScrappingErrorException(String message){
        super(SCRAPPING_ERROR,message, Status.INTERNAL_SERVER_ERROR);
    }

}
