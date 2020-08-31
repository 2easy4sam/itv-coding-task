package com.itv.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PYODto {
    private final int campaignId;
    private final int advertiserId;
    private final String advertiserName;
    private final int breakId;
    private final LocalDateTime dateTime;
    private final int durationInSec;
    private final int spotLength;
    private final int targetTvr;
    private final double pyoPercentage;
    private final int demographicProfileId;
    private final String gender;
    private final String location;
    private final int age;

    @JsonCreator
    private PYODto(@JsonProperty("campaignId") int campaignId,
                   @JsonProperty("advertiserId") int advertiserId,
                   @JsonProperty("advertiserName") String advertiserName,
                   @JsonProperty("breakId") int breakId,
                   @JsonProperty("dateTime") LocalDateTime dateTime,
                   @JsonProperty("durationInSec") int durationInSec,
                   @JsonProperty("spotLength") int spotLength,
                   @JsonProperty("targetTvr") int targetTvr,
                   @JsonProperty("pyoPercentage") double pyoPercentage,
                   @JsonProperty("demographicProfileId") int demographicProfileId,
                   @JsonProperty("gender") String gender,
                   @JsonProperty("location") String location,
                   @JsonProperty("age") int age) {
        this.campaignId = campaignId;
        this.advertiserId = advertiserId;
        this.advertiserName = advertiserName;
        this.breakId = breakId;
        this.dateTime = dateTime;
        this.durationInSec = durationInSec;
        this.spotLength = spotLength;
        this.targetTvr = targetTvr;
        this.pyoPercentage = pyoPercentage;
        this.demographicProfileId = demographicProfileId;
        this.gender = gender;
        this.location = location;
        this.age = age;
    }

    public static class Builder {
        private int campaignId;
        private int advertiserId;
        private String advertiserName;
        private int breakId;
        private LocalDateTime dateTime;
        private int durationInSec;
        private int spotLength;
        private int targetTvr;
        private double pyoPercentage;
        private int demographicProfileId;
        private String gender;
        private String location;
        private int age;

        public Builder withCampaignId(int campaignId) {
            this.campaignId = campaignId;
            return this;
        }

        public Builder withAdvertiserId(int advertiserId) {
            this.advertiserId = advertiserId;
            return this;
        }

        public Builder withAdvertiserName(String advertiserName) {
            this.advertiserName = advertiserName;
            return this;
        }

        public Builder withBreakId(int breakId) {
            this.breakId = breakId;
            return this;
        }

        public Builder withDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withDurationInSec(int durationInSec) {
            this.durationInSec = durationInSec;
            return this;
        }

        public Builder withSpotLength(int spotLength) {
            this.spotLength = spotLength;
            return this;
        }

        public Builder withTargetTvr(int targetTvr) {
            this.targetTvr = targetTvr;
            return this;
        }

        public Builder withPyoPercentage(double pyoPercentage) {
            this.pyoPercentage = pyoPercentage;
            return this;
        }

        public Builder withDemographicProfileId(int demographicProfileId) {
            this.demographicProfileId = demographicProfileId;
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

        public PYODto build() {
            return new PYODto(campaignId, advertiserId, advertiserName, breakId, dateTime, durationInSec, spotLength,
                    targetTvr, pyoPercentage, demographicProfileId, gender, location, age);
        }
    }
}
