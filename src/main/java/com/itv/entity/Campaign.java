package com.itv.entity;

public class Campaign {
    private final int id;
    private final int advertiserId;
    private final int demographicProfileId;
    private final int spotLength;
    private final int targetTVR;

    private Campaign(int id,
                     int advertiserId,
                     int demographicProfileId,
                     int spotLength,
                     int targetTVR) {
        this.id = id;
        this.advertiserId = advertiserId;
        this.demographicProfileId = demographicProfileId;
        this.spotLength = spotLength;
        this.targetTVR = targetTVR;
    }

    public int getId() {
        return id;
    }

    public int getAdvertiserId() {
        return advertiserId;
    }

    public int getDemographicProfileId() {
        return demographicProfileId;
    }

    public int getSpotLength() {
        return spotLength;
    }

    public int getTargetTVR() {
        return targetTVR;
    }

    public static class Builder {
        private int id;
        private int advertiserId;
        private int demographicProfileId;
        private int spotLength;
        private int targetTVR;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withAdvertiserId(int advertiserId) {
            this.advertiserId = advertiserId;
            return this;
        }

        public Builder withDemographicProfile(int demographicProfileId) {
            this.demographicProfileId = demographicProfileId;
            return this;
        }

        public Builder withSpotLength(int spotLength) {
            this.spotLength = spotLength;
            return this;
        }

        public Builder withTargetTVR(int targetTVR) {
            this.targetTVR = targetTVR;
            return this;
        }

        public Campaign build() {
            return new Campaign(id, advertiserId, demographicProfileId, spotLength, targetTVR);
        }
    }
}
