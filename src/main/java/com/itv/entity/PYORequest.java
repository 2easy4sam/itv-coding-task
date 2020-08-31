package com.itv.entity;

public class PYORequest {
    private final int id;
    private final int breakId;
    private final int campaignId;

    private PYORequest(int id,
                       int breakId,
                       int campaignId) {
        this.id = id;
        this.breakId = breakId;
        this.campaignId = campaignId;
    }

    public int getId() {
        return id;
    }

    public int getBreakId() {
        return breakId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public static class Builder {
        private int id;
        private int breakId;
        private int campaignId;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withBreakId(int breakId) {
            this.breakId = breakId;
            return this;
        }

        public Builder withCampaignId(int campaignId) {
            this.campaignId = campaignId;
            return this;
        }

        public PYORequest build() {
            return new PYORequest(id, breakId, campaignId);
        }
    }
}