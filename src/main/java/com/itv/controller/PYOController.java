package com.itv.controller;

import com.itv.dto.PYODto;
import com.itv.payload.BookingRequest;
import com.itv.payload.CancelRequest;
import com.itv.payload.Response;
import com.itv.service.PYOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PYOController {
    private PYOService pyoService;

    public PYOController(PYOService pyoService) {
        this.pyoService = pyoService;
    }

    // book PYO
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<Response> bookPyo(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(pyoService.book(request));
    }

    // cancel PYO
    @RequestMapping(value = "/cancel", method = RequestMethod.DELETE)
    public ResponseEntity<Response> cancelPyo(@RequestBody CancelRequest request) {
        return ResponseEntity.ok(pyoService.cancel(request));
    }

    // list PYOs
    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public ResponseEntity<List<PYODto>> reviewPyoRequests(@RequestParam(value = "dateTime") String dateTime,
                                                          @RequestParam(value = "durationInSec") int durationInSec) {
        return ResponseEntity.ok(pyoService.getPYOs(dateTime, durationInSec));
    }
}
