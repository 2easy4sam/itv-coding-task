package com.itv.service;

import com.itv.dao.*;
import com.itv.dto.PYODto;
import com.itv.entity.*;
import com.itv.payload.BookingRequest;
import com.itv.payload.CancelRequest;
import com.itv.payload.Response;
import com.itv.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultPYOService implements PYOService {
    private final AdvertiserDao advertiserDao;
    private final BreakDao breakDao;
    private final CampaignDao campaignDao;
    private final DemographicProfileDao demographicProfileDao;
    private final PYORequestDao pyoRequestDao;
    private final RatingDao ratingDao;

    public DefaultPYOService(AdvertiserDao advertiserDao,
                             BreakDao breakDao,
                             CampaignDao campaignDao,
                             DemographicProfileDao demographicProfileDao,
                             PYORequestDao pyoRequestDao,
                             RatingDao ratingDao) {
        this.advertiserDao = advertiserDao;
        this.breakDao = breakDao;
        this.campaignDao = campaignDao;
        this.demographicProfileDao = demographicProfileDao;
        this.pyoRequestDao = pyoRequestDao;
        this.ratingDao = ratingDao;
    }

    @Override
    public Response book(BookingRequest request) {
        // get all PYO requests associated with requested break id
        // check if a campaign from the same advertiser has already been booked in
        // for the break
        // IF yes THEN return error
        // ELSE persist

        List<PYORequest> pyoRequests = pyoRequestDao.getByBreakIds(Collections.singletonList(request.getBreakId()));
        List<Integer> campaignIds = pyoRequests.stream()
                .map(PYORequest::getCampaignId)
                .collect(Collectors.toList());
        List<Campaign> campaigns;

        if (campaignIds.isEmpty()) {
            campaigns = Collections.emptyList();
        } else {
            campaigns = campaignDao.getByIds(campaignIds);
        }

        if (campaigns.stream().anyMatch(campaign -> campaign.getAdvertiserId() == request.getAdvertiserId())) {
            return new Response(String.format("A campaign from advertiser %d has already been booked for this slot", request.getAdvertiserId()));
        }

        pyoRequestDao.insert(request.getBreakId(), request.getCampaignId());

        return new Response("PYO request processed");
    }

    @Override
    public Response cancel(CancelRequest request) {
        // cancel a pyo request
        // check if the request actually exists
        // IF yes THEN cancel it
        // ELSE return error
        List<PYORequest> pyoRequests = pyoRequestDao.getById(request.getPyoId());
        if (pyoRequests.isEmpty()) {
            return new Response(String.format("PYO request with id %d does not exist", request.getPyoId()));
        }

        pyoRequestDao.delete(request.getPyoId());

        return new Response(String.format("PYO request with id %d has been cancelled", request.getPyoId()));
    }

    @Override
    public List<PYODto> getPYOs(String dateTimeStr, int durationInSec) {
        LocalDateTime dateTime = DateTimeUtils.parse(dateTimeStr);
        long start = dateTime.toEpochSecond(ZoneOffset.UTC);
        long end = dateTime.plusSeconds(durationInSec).toEpochSecond(ZoneOffset.UTC);

        List<Break> breaks = breakDao.getInRange(start, end);

        if (breaks.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, Break> breakMap = breaks.stream().collect(Collectors.toMap(Break::getId, o -> o));
        List<Integer> breakIds = new ArrayList<>(breakMap.keySet());
        List<Rating> ratings = ratingDao.getByBreakIds(breakIds);

        // any ratings for these breaks?
        if (ratings.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, Rating> ratingMap = ratings.stream().collect(Collectors.toMap(rating -> rating.getBreakId() + " " + rating.getDemographicProfileId(), o -> o));

        List<PYORequest> pyoRequests = pyoRequestDao.getByBreakIds(breakIds);
        // any pyo requests in these breaks?
        if (pyoRequests.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> campaignIds = pyoRequests.stream().map(PYORequest::getCampaignId).collect(Collectors.toList());
        List<Campaign> campaigns = campaignDao.getByIds(campaignIds);
        // any campaigns?
        if (campaigns.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Integer, Campaign> campaignMap = campaigns.stream().collect(Collectors.toMap(Campaign::getId, o -> o));

        List<Integer> advertiserIds = campaigns.stream().map(Campaign::getAdvertiserId).collect(Collectors.toList());
        List<Integer> demographicProfileIds = campaigns.stream().map(Campaign::getDemographicProfileId).collect(Collectors.toList());

        List<Advertiser> advertisers = advertiserDao.getByIds(advertiserIds);
        Map<Integer, Advertiser> advertiserMap = advertisers.stream().collect(Collectors.toMap(Advertiser::getId, o -> o));

        List<DemographicProfile> demographicProfiles = demographicProfileDao.getByIds(demographicProfileIds);
        Map<Integer, DemographicProfile> demographicProfileMap = demographicProfiles.stream().collect(Collectors.toMap(DemographicProfile::getId, o -> o));

        return pyoRequests.stream().map(pyo -> {
            Campaign campaign = campaignMap.get(pyo.getCampaignId());
            Advertiser advertiser = advertiserMap.get(campaign.getAdvertiserId());
            Break breakObject = breakMap.get(pyo.getBreakId());
            String ratingKey = breakObject.getId() + " " + campaign.getDemographicProfileId();
            Rating rating = ratingMap.get(ratingKey);
            DemographicProfile demographicProfile = demographicProfileMap.get(campaign.getDemographicProfileId());

            return new PYODto.Builder()
                    .withCampaignId(pyo.getCampaignId())
                    .withAdvertiserId(campaign.getAdvertiserId())
                    .withAdvertiserName(advertiser.getName())
                    .withBreakId(pyo.getBreakId())
                    .withDateTime(breakObject.getDateTime())
                    .withDurationInSec(breakObject.getDurationInSec())
                    .withSpotLength(campaign.getSpotLength())
                    .withTargetTvr(campaign.getTargetTVR())
                    .withPyoPercentage(rating.getTvr() / campaign.getTargetTVR())
                    .withDemographicProfileId(demographicProfile.getId())
                    .withGender(demographicProfile.getGender())
                    .withLocation(demographicProfile.getLocation())
                    .withAge(demographicProfile.getAge())
                    .build();
        }).collect(Collectors.toList());
    }
}
