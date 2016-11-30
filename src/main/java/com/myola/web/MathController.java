package com.myola.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;

/**
 * Created by olath_000 on 2016-11-29.
 */
@Controller
public class MathController {

    private static final Logger LOG = LoggerFactory.getLogger(MathController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${math.service.base.url}")
    private String baseUrl;

    @PostConstruct
    public void init() {
        LOG.info("Initalized with baseUrl: \"{}\".", baseUrl);
    }

    @RequestMapping("/add")
    public String add(@RequestParam(value = "v1") Integer v1,
                      @RequestParam(value = "v2") Integer v2,
                      Model model) {

        final URI addUri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/add")
                .queryParam("v1", v1)
                .queryParam("v2", v2)
                .build().toUri();
        LOG.info("Calling add method at \"{}\".",
                addUri.toString());
        final Result result = restTemplate.getForObject(addUri, Result.class);

        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("result", result);

        return "add";
    }
}
