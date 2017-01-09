package com.example;

import io.specto.hoverfly.junit.dsl.HttpBodyConverter;
import io.specto.hoverfly.junit.rule.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseBuilder.response;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecondServiceTest {

    @Autowired
    private SecondService subject;

    @ClassRule
    public static HoverflyRule remoteService = HoverflyRule.inSimulationMode(dsl(
            service("example.com").get("/foo/1").willReturn(response().status(404)),
            service("example.com").get("/foo/2").willReturn(response().status(200).body(HttpBodyConverter.json(Foo.valueOf("foo"))))
    ));

    @Test
    public void feignClientShouldReturnEmptyWhenReceiving404() {
        /*
        This test will fail with the following error:
        java.lang.AssertionError:
        Expected :Optional.empty
        Actual   :null
         */
        assertEquals(Optional.empty(), subject.getFoo("1"));
    }

    @Test
    public void feignClientShouldReturnOptionalWhenReceiving200() {
        // This test passes successfully
        assertEquals(Optional.of("foo"), subject.getFoo("2").map(Foo::getItem));
    }

}
