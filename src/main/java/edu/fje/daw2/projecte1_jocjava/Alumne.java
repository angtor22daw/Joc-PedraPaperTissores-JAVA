//package com.example.productorrest;
package edu.fje.daw2.projecte1_jocjava;

/**
 * Classe Alumne que representa una entitat alumne
 * @author sergi.grau@fje.edu
 * @version 1.0 18.11.21
 *
 */
class Alumne {
   private int id;
   private String nom;
   private int nota;

    public Alumne(int id, String nom, int nota) {
        this.id = id;
        this.nom = nom;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNota() {
        return nota;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumne amb les dades {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nota=" + nota +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumne alumne = (Alumne) o;
        return id == alumne.id;
    }
}
