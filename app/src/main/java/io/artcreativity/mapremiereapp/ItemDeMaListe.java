package io.artcreativity.mapremiereapp;

import java.io.Serializable;

public class ItemDeMaListe implements Serializable {
    String title;
    String subTitle;

    public ItemDeMaListe() {
    }

    public ItemDeMaListe(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }
}
