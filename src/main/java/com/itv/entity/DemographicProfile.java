package com.itv.entity;

public class DemographicProfile {
    private final int id;
    // if time allows I would use an enum
    private final String gender;
    private final String location;
    private final int age;

    private DemographicProfile(int id, String gender, String location, int age) {
        this.id = id;
        this.gender = gender;
        this.location = location;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }

    public static class Builder {
        private int id;
        private String gender;
        private String location;
        private int age;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public DemographicProfile build() {
            return new DemographicProfile(id, gender, location, age);
        }
    }
}
