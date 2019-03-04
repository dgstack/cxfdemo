package com.dgstack.cxfdemo.config;

import com.dgstack.cxfdemo.interceptor.InMessageInterceptor;
import com.dgstack.cxfdemo.ws.impl.HelloWorldImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class CxfWsConfiguration {

    private final InMessageInterceptor inMessageInterceptor;

    public CxfWsConfiguration(InMessageInterceptor inMessageInterceptor) {
        this.inMessageInterceptor = inMessageInterceptor;
    }


    @Bean
    public Endpoint endpoint(Bus bus){
        EndpointImpl endpoint = new EndpointImpl(bus, new HelloWorldImpl());
        endpoint.publish("/helloWorld");
        endpoint.getInInterceptors().add(inMessageInterceptor);
        final Map<String, Object> cxf = new LinkedHashMap<>();
        // TODO add Properties

        return endpoint;
    }

}
