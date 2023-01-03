package com.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;

public class Example implements HttpFunction {
  private static final Gson gson = new Gson();
  
  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {

    if(request.getMethod() == "POST") {
      JsonObject body = gson.fromJson(request.getReader(), JsonObject.class);
      
      if(!body.has("name")) {
        return;
      }

      if(!body.has("age")) {
        return;
      }

      People people = new People(
        body.get("name").getAsString(),
        body.get("age").getAsInt()
      );
      
      GeneratePdf.getPdf(people);
      response.setContentType("application/json");
      BufferedWriter writer = response.getWriter();
      writer.write(people.toString());
    } else {
      response.setStatusCode(400);
    }

  }
}
