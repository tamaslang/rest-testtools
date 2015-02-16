package org.talang.rest.testtools.testapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@EnableAutoConfiguration
@ComponentScan({"org.talang.rest.testtools.testapp","org.talang.rest.testtools.verifier"})
public class TestApplication {
    @Resource
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestApplication.class);

    private RelaxedPropertyResolver dataSourcePropertyResolver;

    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            LOGGER.warn("No Spring profile configured, running with default configuration");
        } else {
            LOGGER.info("Running with Spring profile(s) : {}", env.getActiveProfiles());
            this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "jmx.");
        }
    }



    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestApplication.class);
        app.setShowBanner(false);
        app.run(args);
    }


    /**
     * REST
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RestTemplate restTemplate() {
        int readTimeout = Integer.parseInt(env.getProperty("gateway.configuration.readTimeout"));
        int connectTimeout = Integer.parseInt(env.getProperty("gateway.configuration.connectTimeout"));
        LOGGER.debug("Create RestTemplate with readTimeout={}, connectTimeout={}", readTimeout, connectTimeout);
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(connectTimeout);
        httpRequestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter());
        return restTemplate;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    private ObjectMapper objectMapper() {
        JodaModule jodaModule = new JodaModule();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

}
