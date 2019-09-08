package com.dgordon.taxcalc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Logger;


import javax.json.*;

@RestController
public class TaxCalcController {
    private static final Logger LOG = Logger.getLogger(TaxCalcController.class.getName());

    @RequestMapping(method = RequestMethod.POST, value = "/api")
    public ResponseEntity<?> testJSON(@RequestBody String input) {

        JsonReader reader = Json.createReader((InputStream) new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8"))));
        JsonObject clientJson = reader.readObject();
        String returnData = "";
        StringBuilder sb = new StringBuilder();

        LOG.info(sb.append("recieved data: ").append(clientJson.toString()).toString()) ;
        sb = new StringBuilder();

        RestClient client = new RestClient();
        returnData = client.post(clientJson.getJsonObject("path").get("year").toString(), clientJson.get("body").toString()) ;

        LOG.info( sb.append("data to return: ").append(returnData).toString() );
        sb = new StringBuilder()

        return new ResponseEntity<>(returnData, HttpStatus.ACCEPTED);
    }
}