package com.example.practicaexamen.data.model;

public class Offer implements Comparable<Offer>{
    private Type type;
    private String name;

    public Offer() {
    }

    public Offer(String name) {
        this.name = name;
    }

    public Offer(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        return getName().equals(offer.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Offer o) {
        return this.getName().compareTo(o.getName());
    }
}
