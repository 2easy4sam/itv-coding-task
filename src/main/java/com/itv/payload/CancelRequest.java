package com.itv.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CancelRequest {
    private int pyoId;

    @JsonCreator
    public CancelRequest(@JsonProperty("pyoId") int pyoId) {
        this.pyoId = pyoId;
    }

    public int getPyoId() {
        return pyoId;
    }
}
