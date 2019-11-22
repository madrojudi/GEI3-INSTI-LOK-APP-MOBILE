package io.artcreativity.mapremiereapp.entities;

import java.io.Serializable;

public class ItemDeMaListe implements Serializable {
    public String title;
    public String subTitle;

    public ItemDeMaListe() {
    }

    public ItemDeMaListe(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }
}
