package com.skypro.telematics.controller;

import com.skypro.telematics.dto.LogIndication;
import com.skypro.telematics.dto.SerialSecretDTO;
import com.skypro.telematics.model.Token;
import com.skypro.telematics.service.IndicationService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

@RestController
@RequestMapping("/indications")
public class IndicationController {

    @Autowired
    private IndicationService indicationService;

    @PostMapping
    public void indication(@RequestBody LogIndication logIndication, Authentication authentication) {
        Token principal= (Token) authentication.getPrincipal();
        indicationService.save(principal.getSerialSecret(), logIndication);
    }

    @GetMapping("/{serial}")
    public Double avg(@PathVariable String serial){
        return indicationService.calculateAvgIndication(serial);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SerialSecretDTO serialSecretDTO) throws JSONException {

    return ResponseEntity.ok().body(new JSONObject().put("token", indicationService.login(serialSecretDTO)).toString());
    }
}
