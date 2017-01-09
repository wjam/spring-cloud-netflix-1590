package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Optional;

@FeignClient(value = "second", url = "${second.url}", decode404 = true)
public interface SecondService {
    @RequestMapping(method = RequestMethod.GET, value = "/foo/{id}")
    Optional<Foo> getFoo(@PathVariable("id") String id);
}
