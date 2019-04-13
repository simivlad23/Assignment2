package com.ps.lab.model;

public class Comanda {
    private int idComanda;
    private int idProd;
    private int idCli;
    private int cantitate;

    public Comanda(int idComanda, int idProd, int idCli, int cantitate) {
        this.idComanda = idComanda;
        this.idProd = idProd;
        this.idCli = idCli;
        this.cantitate = cantitate;
    }

    public Comanda() {
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getIdProd() {
        return idProd;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "idComanda=" + idComanda +
                ", idProd=" + idProd +
                ", idCli=" + idCli +
                ", cantitate=" + cantitate +
                '}';
    }


    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }



}
