package org.talang.rest.testtools.testapp;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class ApplicationSetupTest {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExampleResource exampleResource;

    @Test
    public void contextSetupTest(){
        assertThat(restTemplate,notNullValue());
    }

    @Test
    public void restResourceSetupTest(){
        assertThat(exampleResource,notNullValue());
    }
}
