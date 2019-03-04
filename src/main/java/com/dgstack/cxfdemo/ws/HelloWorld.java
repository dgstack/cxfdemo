package com.dgstack.cxfdemo.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWorld {
    @WebMethod(action = "sayHi")
    String sayHi(String text);
}
