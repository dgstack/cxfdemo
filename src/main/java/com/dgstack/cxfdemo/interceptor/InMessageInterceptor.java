package com.dgstack.cxfdemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.interceptor.EndpointSelectionInterceptor;
import org.apache.cxf.binding.soap.interceptor.ReadHeadersInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Component
public class InMessageInterceptor extends AbstractPhaseInterceptor<Message> {


    public InMessageInterceptor() {
        super(Phase.READ);
        addAfter(ReadHeadersInterceptor.class.getName());
        addAfter(EndpointSelectionInterceptor.class.getName());
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        log.info("message : {}", message);
        System.out.println("message = " + message);
        List<Header> headers = CastUtils.cast((List<?>) message.get(Header.HEADER_LIST));
        for (Header header : headers) {
            log.info("header : {}", header);
        }
    }

//    @Override
//    protected Logger getLogger() {
//        return null;
//    }
}
