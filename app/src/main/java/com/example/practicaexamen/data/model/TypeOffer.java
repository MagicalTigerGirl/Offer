package com.example.practicaexamen.data.model;

public class TypeOffer implements Comparable<TypeOffer> {
    private Type type;
    private String name;

    public TypeOffer() {
    }

    public TypeOffer(Type type, String name) {
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
        if (!(o instanceof TypeOffer)) return false;

        TypeOffer typeOffer = (TypeOffer) o;

        return getName().equals(typeOffer.getName());
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
    public int compareTo(TypeOffer o) {
        return this.getName().compareTo(o.getName());
    }
}
