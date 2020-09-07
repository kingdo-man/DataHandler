package com.me.datahandler;

import com.me.datahandler.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/getdatas",method = RequestMethod.GET)
    public List<Map<String, Object>> getData(@RequestParam(value = "st") Integer st,
                                             @RequestParam(value = "en") Integer en) {

        return testService.getData(st,en);
    }
}
