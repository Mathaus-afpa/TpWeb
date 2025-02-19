package com.example.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class CustomProperties {

 private String url;

 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }
}