package com.k1rard.app.config;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

/**
 * @author k1rard
 * Class factory that warapped exception with DefaultExceptionHandler Class.
 */
public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {

    public DefaultExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
        super(wrapped);
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler parentHandler = getWrapped().getExceptionHandler();
        return new DefaultExceptionHandler(parentHandler);
    }

}
