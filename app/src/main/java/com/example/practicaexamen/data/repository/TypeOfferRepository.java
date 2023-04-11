package com.example.practicaexamen.data.repository;

import com.example.practicaexamen.data.model.Type;
import com.example.practicaexamen.data.model.TypeOffer;

import java.util.ArrayList;

public class TypeOfferRepository {
    private ArrayList<TypeOffer> list;
    private static TypeOfferRepository instance;

    public TypeOfferRepository() {
        this.list = new ArrayList<>();
        inicializar();
    }

    public ArrayList<TypeOffer> getList() {
        return list;
    }

    public static TypeOfferRepository getInstance() {
        if (instance == null)
            instance = new TypeOfferRepository();
        return instance;
    }

    private void inicializar() {
        list.add(new TypeOffer(Type.HOME, "HOME"));
        list.add(new TypeOffer(Type.ELECTRONIC, "ELECTRONIC"));
        list.add(new TypeOffer(Type.GAMES, "GAMES"));
    }
}
