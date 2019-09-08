package com.dgordon.taxcalc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


import javax.json.*;

@RestController
public class TaxCalcController {
    private static final Logger LOG = Logger.getLogger(TaxCalcController.class.getName());
    // private URL taxUrl;
    // Map<String, String> parameters = new HashMap<String, String>();
    // private HttpURLConnection conn;

    public TaxCalcController() {
        // try {
        //     taxUrl = new URL("https://taxee.io/api/v2/federal/2019");
        //     parameters.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUElfS0VZX01BTkFHRVIiLCJodHRwOi8vdGF4ZWUuaW8vdXNlcl9pZCI6IjVkNzJmNGY3NDRmMzYwMWEyODMwYjJhYiIsImh0dHA6Ly90YXhlZS5pby9zY29wZXMiOlsiYXBpIl0sImlhdCI6MTU2NzgxNDkwM30.FiVjGqO_HrHtODYLUBqbtnQm48sVxGPYRyTrzWSobks");
        //     conn = (HttpURLConnection) taxUrl.openConnection();
        //     conn.setRequestMethod("GET");
        // } catch (IOException ioe) {
        //     LOG.severe("Malformed URL");
        //     taxUrl = null;
        // }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api")
    public ResponseEntity<?> testJSON(@RequestBody String input) {

        JsonReader reader = Json.createReader((InputStream) new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8"))));
        JsonObject clientJson = reader.readObject();
        String returnData = "";
        StringBuilder sb = new StringBuilder();

        LOG.info(sb.append("recieved data: ").append(clientJson.toString()).toString()) ;
        sb = new StringBuilder();

        // try {
        //     taxJson = Json.createReader(conn.getInputStream()).readObject();
        // } catch (IOException e ) {
        //     LOG.severe(e.getMessage());
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        // returnData = (taxJson == null)? "" : taxJson.toString();
        RestClient client = new RestClient();
        returnData = client.post(clientJson.getJsonObject("path").get("year").toString(), clientJson.get("body").toString()) ;

        return new ResponseEntity<>(returnData, HttpStatus.ACCEPTED);
    }
}