package com.NPU.上海出差.模拟事件仿真;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

public class SendRecvApp {

    private static final Log logger = LogFactory.getLog(SendRecvApp.class);

    public static void main(String[] args) {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("helloWorldDemo.xml", SendRecvApp.class);
        MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
        PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);
        inputChannel.send(new GenericMessage<String>("World"));
        logger.info("==> HelloWorldDemo: " + outputChannel.receive(0).getPayload());
        context.close();
    }
}
