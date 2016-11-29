package com.myola.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by olath_000 on 2016-11-29.
 */
@Controller
public class MathController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${math.service.base.url}")
    private String baseUrl;

    @RequestMapping("/add")
    public String add(@RequestParam(value = "v1") Integer v1,
                           @RequestParam(value = "v2") Integer v2,
                           Model model) {

        final Result result = restTemplate.getForObject(baseUrl + "/add", Result.class, v1, v2);

        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("result", result);

        return "add";
    }
}
