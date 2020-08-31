package com.itv.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingRequest {
    private int breakId;
    private int campaignId;
    private int advertiserId;

    @JsonCreator
    public BookingRequest(@JsonProperty("breakId") int breakId,
                          @JsonProperty("campaignId") int campaignId,
                          @JsonProperty("advertiserId") int advertiserId) {
        this.breakId = breakId;
        this.campaignId = campaignId;
        this.advertiserId = advertiserId;
    }

    public int getBreakId() {
        return breakId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public int getAdvertiserId() {
        return advertiserId;
    }
}
