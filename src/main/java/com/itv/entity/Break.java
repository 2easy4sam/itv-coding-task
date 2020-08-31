package com.itv.entity;

import java.time.LocalDateTime;

public class Break {
    private final int id;
    // to be converted to LocalDateTime
    private final LocalDateTime dateTime;
    private final int durationInSec;

    private Break(int id, LocalDateTime dateTime, int durationInSec) {
        this.id = id;
        this.dateTime = dateTime;
        this.durationInSec = durationInSec;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDurationInSec() {
        return durationInSec;
    }

    public static class Builder {
        private int id;
        private LocalDateTime dateTime;
        private int durationInSec;

        public Builder withId(int id) {
            this.id = id;
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

        public Break build() {
            return new Break(id, dateTime, durationInSec);
        }
    }
}
