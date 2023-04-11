package com.example.practicaexamen.data.model;

import java.util.Comparator;

public class OfferComparatorType implements Comparator<Offer> {
    @Override
    public int compare(Offer o1, Offer o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
