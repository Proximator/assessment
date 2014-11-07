package com.goeuro.service;

import com.goeuro.model.Position;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class PositionService {

    public static final int OK_STATUS = 200;
    public static final String API_END_POINT = "http://api.goeuro.com/api/v2/position/suggest/en/";

    public Position[] query(String endPoint, String location){

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client
                .target(endPoint + location);

        Response response = target.request().accept("*/*").header("Content-Type", "text/plain").get(Response.class);

        if (response.getStatus() != OK_STATUS) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        } else {
            Position[] positions = response.readEntity(Position[].class);
            return positions;
        }
    }
    public Position[] query(String location) {
        return query(API_END_POINT, location);
    }



}
