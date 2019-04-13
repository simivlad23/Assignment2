package com.ps.lab.model;

public class Comanda extends  EntityObject{
    private int idProd;
    private int cantitate;

    public Comanda(int idProd, int cantitate) {

        this.idProd = idProd;

        this.cantitate = cantitate;
    }

    public Comanda() {
    }


    public int getIdProd() {
        return idProd;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                ", idProd=" + idProd +
                ", cantitate=" + cantitate +
                '}';
    }


    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }



}
