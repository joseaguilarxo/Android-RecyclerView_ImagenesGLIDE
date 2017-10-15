package com.joseaguilar.llenarrecyclerview_con_imagenesglide.Clases;

/**
 * Created by josea on 15/10/2017.
 */

public class Pokemon {
    private int number;
    private String name;
    private String url;

    public int setNumber() {
        return number;
    }

    public int getNumber() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
