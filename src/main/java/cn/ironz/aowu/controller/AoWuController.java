package cn.ironz.aowu.controller;

import cn.ironz.aowu.pojo.Transport;
import cn.ironz.aowu.service.Encoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aowu")
public class AoWuController {


    @ResponseBody
    @RequestMapping(value = "/encode", produces = "application/json")
    public String encode(@RequestBody Transport transport) {
        Encoder encoder = new Encoder();
        encoder.setKeys(transport.getKeys());
        return encoder.encodeParagraph(transport.getValue());
    }

    @ResponseBody
    @RequestMapping(value = "/decode", produces = "application/json")
    public String decode(@RequestBody Transport transport) {
        Encoder encoder = new Encoder();
        encoder.setKeys(transport.getKeys());
        return encoder.decodeParagraph(transport.getValue());
    }
}
