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
    public static int contador = 0;
    private static String movJug1 = "";
    private static String movJug2 = "";
    private static String missatge = "";

    public Api() {
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
        for (Partida p : partides) {
            if (p.getCodiPartida() == codiPartida) {
                return Response.status(200).entity("La partida ja existeix!").build();
            }
        }

        partides.add(new Partida(codiPartida,jugador, moviment,torn,vicJug1,vicJug2));
        return Response.status(200).entity("La partida amb el codi "+ codiPartida +" ha estat creada!").build();
    }

    @PUT
    @Path("/moureJugador/codiPartida/jugador/tipusMoviment")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response modificarAlumne(@FormParam("codiPartida") int codiPartida, @FormParam("jugador") String jugador,
                                    @FormParam("tipusMoviment") String tipusMoviment) {

        if (!tipusMoviment.equals("pedra") && !tipusMoviment.equals("paper") && !tipusMoviment.equals("tisores")) {
            return Response.status(200).entity("El moviment ha de ser paper, pedra o tisores!").build();
        }
        for (Partida p : partides) {
            if (p.getCodiPartida() == codiPartida) {
                if (p.getTorn().equals(jugador)) {
                    p.setJugador(jugador);
                    if (jugador.equals("jug1")) {
                        movJug1 = tipusMoviment;
                        p.setTorn("jug2");
                        ++contador;
                    } else if (jugador.equals("jug2")) {
                        movJug2 = tipusMoviment;
                        p.setTorn("jug1");
                        ++contador;
                    }
                    p.setMoviment(String.valueOf(contador));
                } else {
                    return Response.status(200).entity("No es el teu torn, espera a que l'altre jugador faci el seu moviment").build();
                }
            }/*else{
                return Response.status(200).entity("La partida no existeix!").build();
            }*/
            if (contador == 2) {
                contador = 0;
                if (movJug1.equals("paper") && movJug2.equals("pedra")) {
                    p.setVicJug1(p.getVicJug1() + 1);
                    missatge = "El jugador 1 ha escollit PAPER, el Jugador 2 PEDRA. \nEl Jugador 1 ha guanyat aquesta ronda.";
                } else if (movJug1.equals("pedra") && movJug2.equals("paper")) {
                    p.setVicJug2(p.getVicJug2() + 1);
                    missatge = "El jugador 1 ha escollit PEDRA, el Jugador 2 PAPER. \nEl Jugador 2 ha guanyat aquesta ronda.";
                } else if (movJug1.equals("paper") && movJug2.equals("tisores")) {
                    p.setVicJug2(p.getVicJug2() + 1);
                    missatge = "El jugador 1 ha escollit PAPER, el Jugador 2 TISORES. \nEl Jugador 2 ha guanyat aquesta ronda.";
                } else if (movJug1.equals("tisores") && movJug2.equals("paper")) {
                    p.setVicJug1(p.getVicJug1() + 1);
                    missatge = "El jugador 1 ha escollit TISORES, el Jugador 2 PAPER. \nEl Jugador 1 ha guanyat aquesta ronda.";
                } else if (movJug1.equals("pedra") && movJug2.equals("tisores")) {
                    p.setVicJug1(p.getVicJug1() + 1);
                    missatge ="El jugador 1 ha escollit PEDRA, el Jugador 2 TISORES. \nEl Jugador 1 ha guanyat aquesta ronda.";
                } else if (movJug1.equals("tisores") && movJug2.equals("pedra")) {
                    p.setVicJug2(p.getVicJug2() + 1);
                    missatge = "El jugador 1 ha escollit tisores, el Jugador 2 PEDRA. \nEl Jugador 2 ha guanyat aquesta ronda.";
                }else{
                    movJug1 = "";
                    movJug2 = "";
                    p.setTorn("jug1");
                    return Response.status(200).entity("Els dos jugadors heu triat "+tipusMoviment +". Es un EMPAT!").build();
                }
                // el return deberia ir por aca
            }
            if (p.getVicJug1() == 3) {
                return Response.status(200).entity(missatge+"\nFELICITATS jugador 1, has guanyat la partida!").build();
            } else if (p.getVicJug2() == 3) {
                return Response.status(200).entity(missatge+"\nFELICITATS jugador 2, has guanyat la partida!").build();
            } else if( contador == 0){
                return Response.status(200).entity(missatge).build();
            }
            return Response.status(200).entity("El jugador " + p.getTorn() + " ha de fer el seu moviment!").build();
        }
        return Response.status(200).entity("La partida no existeix??? bobo").build();
    }

    @Path("/acabarJoc/codiPartida")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String esborrarPartida(@FormParam("codiPartida") int codiPartida) {
        for (Partida p : partides) {
            if (p.getCodiPartida() == codiPartida) {
                partides.remove(p);
                return "La partida amb el codi "+ codiPartida +" ha estat eliminada!";
            }else{
                return "No pots eliminar una partida que no existeix!";
            }
        }
        return "";
    }
}
