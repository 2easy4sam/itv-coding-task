package com.itv.entity;

public class Advertiser {
    private final int id;
    private final String name;

    private Advertiser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Advertiser build() {
            return new Advertiser(id, name);
        }
    }
}
