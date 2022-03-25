package com.sacombank.payment.infrastructure.configuration;

import java.io.IOException;

import javax.annotation.PreDestroy;

import com.sacombank.payment.application.PaymentRuleValidatorGrpcServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GrpcServer implements ApplicationRunner {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(GrpcServer.class);
    @Value(value = "${grpc.server.port}")
    private int port;
    
    private PaymentRuleValidatorGrpcServer paymentRuleValidatorGrpcServer;
    public GrpcServer(PaymentRuleValidatorGrpcServer paymentRuleValidatorGrpcServer) {
        this.paymentRuleValidatorGrpcServer = paymentRuleValidatorGrpcServer;
    }

    @Override
    public void run(ApplicationArguments args) throws  IOException, InterruptedException  {
        Server server = ServerBuilder.forPort(port)
        .addService(paymentRuleValidatorGrpcServer).build();

      logger.info("Starting Grpc server at port {}", port);
      server.start();
      logger.info("Server started!");
      server.awaitTermination();
      logger.info("application is ready to serve...");       
    }

    @Bean
	InitializingBean populateData() {
		return () -> {
            // load data from DB
            logger.info("populated data");
		};
	}
    
    @PreDestroy
    public void onExit() {
        logger.info("application stopped");
    }
    
}

