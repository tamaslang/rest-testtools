package org.talang.rest.testtools.testapp;

import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/example")
public class ExampleResource {

    @RequestMapping(value = "/stringlist",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> getStringList() {
        return Arrays.asList(new String[]{"a","b"});
    }

    @RequestMapping(value = "/stringlist/reverse",
            method = RequestMethod.PUT,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<String> takeStringList(@RequestBody List<String> strings) {
        return Lists.reverse(strings);
    }


}
