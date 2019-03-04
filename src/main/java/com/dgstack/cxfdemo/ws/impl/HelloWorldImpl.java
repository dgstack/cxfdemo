package com.dgstack.cxfdemo.ws.impl;

import com.dgstack.cxfdemo.ws.HelloWorld;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

@WebService(endpointInterface = "com.dgstack.cxfdemo.ws.HelloWorld", serviceName = "HelloWorld")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
@org.apache.cxf.interceptor.InInterceptors(interceptors = {"org.apache.cxf.interceptor.LoggingInInterceptor"})
@org.apache.cxf.interceptor.OutInterceptors(interceptors = {"org.apache.cxf.interceptor.LoggingOutInterceptor"})
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHi(String text) {
        return "Hi " + text;
    }
}
