package com.example.practicaexamen.data.repository;

import com.example.practicaexamen.data.model.Offer;
import com.example.practicaexamen.data.model.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OfferRepository {
    private ArrayList<Offer> list;
    private ArrayList<Offer> listFiltred;
    private static OfferRepository instance;

    public OfferRepository() {
        this.list = new ArrayList<>();
        this.listFiltred = new ArrayList<>();
        inicializar();
    }

    public ArrayList<Offer> getList() {
        return list;
    }

    public ArrayList<Offer> getListFiltred() {
        return listFiltred;
    }

    public static OfferRepository getInstance() {
        if (instance == null)
            instance = new OfferRepository();
        return instance;
    }

    private void inicializar() {
        list.add(new Offer(Type.HOME, "Muebles"));
        list.add(new Offer(Type.ELECTRONIC, "Lavadora"));
        list.add(new Offer(Type.GAMES, "Nintendo"));
        Collections.sort(list);
    }

    public ArrayList<Offer> home() {
        ArrayList<Offer> data = new ArrayList<>();
        for (Offer offer : list){
            if (offer.getType().equals(Type.HOME))
                data.add(offer);
        }
        return data;
    }

    public ArrayList<Offer> electronic() {
        ArrayList<Offer> data = new ArrayList<>();
        for (Offer offer : list){
            if (offer.getType().equals(Type.ELECTRONIC))
                data.add(offer);
        }
        return data;
    }

    public ArrayList<Offer> games() {
        ArrayList<Offer> data = new ArrayList<>();
        for (Offer offer : list){
            if (offer.getType().equals(Type.GAMES))
                data.add(offer);
        }
        return data;
    }

    public boolean check(Offer offer) {
        if (list.contains(new Offer(offer.getName())))
            return false;
        return true;
    }

    public boolean add(Offer offer) {
        list.add(offer);
        Collections.sort(list);
        return true;
    }
}
