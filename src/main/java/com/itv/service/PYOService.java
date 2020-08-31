package com.itv.service;

import com.itv.dto.PYODto;
import com.itv.payload.*;

import java.util.List;

public interface PYOService {
    Response book(BookingRequest request);

    Response cancel(CancelRequest request);

    List<PYODto> getPYOs(String dateTime, int durationInSec);
}
