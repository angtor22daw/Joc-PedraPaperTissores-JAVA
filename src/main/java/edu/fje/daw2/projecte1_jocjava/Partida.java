package edu.fje.daw2.projecte1_jocjava;

public class Partida {
    private int codiPartida;
    private String jugador;
    private String moviment;
    private String torn;
    private int vicJug1;
    private int vicJug2;

    public Partida(int codiPartida, String jugador, String moviment, String torn, int vicJug1, int vicJug2) {
        this.codiPartida = codiPartida;
        this.jugador = jugador;
        this.moviment = moviment;
        this.torn = "jug1";
        this.vicJug1 = vicJug1;
        this.vicJug2 = vicJug2;
    }

    public int getCodiPartida() {
        return codiPartida;
    }

    public void setCodiPartida(int codiPartida) {
        this.codiPartida = codiPartida;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getMoviment() {
        return moviment;
    }

    public void setMoviment(String moviment) {
        this.moviment = moviment;
    }

    public String getTorn() {
        return torn;
    }

    public void setTorn(String torn) {
        this.torn = torn;
    }

    public int getVicJug1() {
        return vicJug1;
    }

    public void setVicJug1(int vicJug1) {
        this.vicJug1 = vicJug1;
    }

    public int getVicJug2() {
        return vicJug2;
    }

    public void setVicJug2(int vicJug2) {
        this.vicJug2 = vicJug2;
    }

    @Override
    public String toString() {
        return "Partida{" + "codiPartida=" + codiPartida + ", jugador=" + jugador + ", moviment=" + moviment + ", torn=" + torn + ", vicJug1=" + vicJug1 + ", vicJug2=" + vicJug2 + '}';
    }
}
