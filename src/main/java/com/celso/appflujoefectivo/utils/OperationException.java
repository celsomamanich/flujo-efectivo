package com.celso.appflujoefectivo.utils;

public class OperationException extends RuntimeException
{

    public OperationException(String mensaje){ super(mensaje); }

    public OperationException(String mensaje, Throwable e)
    {
        super(mensaje, e);
    }

}
