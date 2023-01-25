/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.example.productorrest;
package edu.fje.daw2.projecte1_jocjava;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("daw2")
/**
 * Servei web rest amb Java
 * URL
 * localhost:8080/api/daw2/consultarTots
 * Pol Sanz i Angel Torres
 *
 */
public class Api {

    @Context
    private UriInfo context;
    private static List<Partida> partides = new ArrayList<>();

    private static List<Alumne> alumnes = new ArrayList<>();

    public Api() {

        if (alumnes.size() == 0) {
            alumnes.add(new Alumne(2, "JOAN", 7));
            alumnes.add(new Alumne(1, "SERGI", 8));
            alumnes.add(new Alumne(3, "ANNA", 6));
        }

    }

    @GET
    @Path("/consultarEstatPartida/partides")
    @Produces(MediaType.APPLICATION_JSON)
    public String consultarEstatPartida() {
        return partides.toString();
    }

    @GET
    @Path("/consultarEstatPartida/{codiPartida}")
    @Produces(MediaType.TEXT_PLAIN)
    public String consultarPartida(@PathParam("codiPartida") int codiPartida) {
        for (Partida p : partides) {
            if (p.getCodiPartida() == codiPartida) {
                return p.toString();
            }else{
                return "La partida no existeix";
            }
        }
        return "";
    }

    @POST
    @Path("/iniciarJoc/codiPartida")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response crearPartida(@FormParam("codiPartida") int codiPartida, @FormParam("jugador") String jugador,
                                 @FormParam("moviment") String moviment,@FormParam("torn") String torn, @FormParam("vicJug1") int vicJug1,
                                 @FormParam("vicJug2") int vicJug2) {

        partides.add(new Partida(codiPartida,jugador, moviment,torn,vicJug1,vicJug2));
        return Response.status(200).entity("La partida ha estat creada!").build();
    }

    @Path("/acabarJoc/{codiPartida}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String esborrarPartida(@PathParam("codiPartida") int codiPartida) {
        for (Partida p : partides) {
            if (p.getCodiPartida() == codiPartida) {
                partides.remove(p);
                return "Partida eliminada!";
            }else{
                return "No pots eliminar una partida que no existeix!";
            }
        }
        return "";
    }

    @Path("/consultarTOTS")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String consultarTotsAlumnes() {
        return alumnes.toString();
    }

    @Path("/consultarUn/{alumne}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String consultarUn(@PathParam("alumne") int id) {
        Alumne temp = new Alumne(id, "", 0);
        int pos = alumnes.indexOf(temp);
        return alumnes.get(pos).toString();
    }

    @Path("/consultarUnQuery")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String consultarUnQuery(@QueryParam("id") int id) {
        Alumne temp = new Alumne(id, "", 0);
        int pos = alumnes.indexOf(temp);
        return alumnes.get(pos).toString();
    }


    @PUT
    @Path("/modificarAlumne")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response modificarAlumne(@FormParam("id") int id,
                                @FormParam("nom") String nouNom, @FormParam("nota") int novaNota) {

        Alumne temp = new Alumne(id, nouNom, novaNota);
        int pos = alumnes.indexOf(temp);
        alumnes.get(pos).setNom(nouNom);
        alumnes.get(pos).setNota(novaNota);
        return Response.status(200).entity("alumne modificat").build();
    }

    @POST
    @Path("/afegirAlumne")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response afegirAlumne(@FormParam("id") int id,
                                 @FormParam("nom") String nom, @FormParam("nota") int nota) {

        alumnes.add(new Alumne(id, nom, nota));
        return Response.status(200).entity("alumne creat").build();
    }

    @Path("/esborrarAlumne/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public void esborrarAlumne(@PathParam("id") int id) {
        Alumne temp = new Alumne(id, "", 0);
        int pos = alumnes.indexOf(temp); // -1
        alumnes.remove(pos); //ERROR

    }

}
