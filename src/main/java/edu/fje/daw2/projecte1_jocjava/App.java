//package com.example.productorrest;
package edu.fje.daw2.projecte1_jocjava;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.Path;

@ApplicationPath("/api")
public class App extends Application {
//PROCESSORS D', INICIALTZACIÓ, bd, xml, sòcols (sockets), etc...
    @GET
    @Path("/hola")
    public String hola() {
        return "Hola";
    }

}



