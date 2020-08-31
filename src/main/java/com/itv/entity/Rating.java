package com.itv.entity;

public class Rating {
    private final int id;
    private final int breakId;
    private final int demographicProfileId;
    private final int tvr;

    private Rating(int id, int breakId, int demographicProfileId, int tvr) {
        this.id = id;
        this.breakId = breakId;
        this.demographicProfileId = demographicProfileId;
        this.tvr = tvr;
    }

    public int getId() {
        return id;
    }

    public int getBreakId() {
        return breakId;
    }

    public int getDemographicProfileId() {
        return demographicProfileId;
    }

    public int getTvr() {
        return tvr;
    }

    public static class Builder {
        private int id;
        private int breakId;
        private int demographicProfileId;
        private int tvr;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withBreakId(int breakId) {
            this.breakId = breakId;
            return this;
        }

        public Builder withDemographicProfile(int demographicProfileId) {
            this.demographicProfileId = demographicProfileId;
            return this;
        }

        public Builder withTvr(int tvr) {
            this.tvr = tvr;
            return this;
        }

        public Rating build() {
            return new Rating(id, breakId, demographicProfileId, tvr);
        }
    }
}
