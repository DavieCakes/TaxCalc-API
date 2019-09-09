package com.dgordon.taxcalc.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class RestClient {

  private String server = "https://taxee.io/api/v2/calculate/2019";
  private RestTemplate rest;
  private HttpHeaders headers;
  private HttpStatus status;

  private static final Logger LOG = Logger.getLogger(RestClient.class.getName());

  public RestClient() {
    this.rest = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    // headers.add("Content-Type", "application/x-www-form-urlencoded");
    // headers.add("Accept", "*/*");
    headers.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUElfS0VZX01BTkFHRVIiLCJodHRwOi8vdGF4ZWUuaW8vdXNlcl9pZCI6IjVkNzJmNGY3NDRmMzYwMWEyODMwYjJhYiIsImh0dHA6Ly90YXhlZS5pby9zY29wZXMiOlsiYXBpIl0sImlhdCI6MTU2NzgxNDkwM30.FiVjGqO_HrHtODYLUBqbtnQm48sVxGPYRyTrzWSobks");
  }

  public String post(String body) {
    
    LOG.info( (new StringBuilder()).append("recieved request data: ").append(body).toString() );
    HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
    LOG.info(requestEntity.getBody());
    try {
      ResponseEntity<String> responseEntity = rest.exchange(server, HttpMethod.POST, requestEntity, String.class);
      this.setStatus(responseEntity.getStatusCode());
      return responseEntity.getBody();
    } catch (HttpServerErrorException | HttpClientErrorException e) {
      LOG.severe(e.getMessage());
      LOG.severe(e.getResponseBodyAsString());
      LOG.severe(e.getResponseHeaders().toString());
      LOG.severe(requestEntity.toString());

    }
    return "";
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  } 
}