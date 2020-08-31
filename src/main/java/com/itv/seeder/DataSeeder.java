package com.itv.seeder;

import com.itv.dao.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * This class is responsible for injecting dummy data into database
 */
public class DataSeeder {

    public DataSeeder(AdvertiserDao advertiserDao,
                      BreakDao breakDao,
                      CampaignDao campaignDao,
                      DemographicProfileDao demographicProfileDao,
                      PYORequestDao pyoRequestDao,
                      RatingDao ratingDao) {
        advertiserDao.createTable();
        breakDao.createTable();
        campaignDao.createTable();
        demographicProfileDao.createTable();
        pyoRequestDao.createTable();
        ratingDao.createTable();

        advertiserDao.insert("Coke");       // 1
        advertiserDao.insert("Pepsi");      // 2
        advertiserDao.insert("Fanta");      // 3

        breakDao.insert(LocalDateTime.of(2020, 8, 30, 9, 0).toEpochSecond(ZoneOffset.UTC), 90);
        breakDao.insert(LocalDateTime.of(2020, 8, 30, 10, 5).toEpochSecond(ZoneOffset.UTC), 90);
        breakDao.insert(LocalDateTime.of(2020, 8, 30, 11, 10).toEpochSecond(ZoneOffset.UTC), 90);

        demographicProfileDao.insert("male", "London", 20);     // 1
        demographicProfileDao.insert("male", "London", 30);     // 2
        demographicProfileDao.insert("male", "London", 40);     // 3
        demographicProfileDao.insert("female", "London", 20);   // 4
        demographicProfileDao.insert("female", "London", 30);   // 5
        demographicProfileDao.insert("female", "London", 40);   // 6

        campaignDao.insert(1, 1, 60, 10);
        campaignDao.insert(2, 2, 60, 15);
        campaignDao.insert(3, 3, 60, 8);
        campaignDao.insert(1, 4, 30, 30);
        campaignDao.insert(2, 5, 30, 16);
        campaignDao.insert(3, 6, 35, 11);

        ratingDao.insert(1, 1, 10);
        ratingDao.insert(1, 2, 10);
        ratingDao.insert(1, 3, 10);
        ratingDao.insert(1, 4, 10);
        ratingDao.insert(1, 5, 10);
        ratingDao.insert(1, 6, 10);

        ratingDao.insert(2, 1, 5);
        ratingDao.insert(2, 2, 5);
        ratingDao.insert(2, 3, 5);
        ratingDao.insert(2, 4, 5);
        ratingDao.insert(2, 5, 5);
        ratingDao.insert(2, 6, 5);

        ratingDao.insert(3, 1, 3);
        ratingDao.insert(3, 2, 3);
        ratingDao.insert(3, 3, 3);
        ratingDao.insert(3, 4, 3);
        ratingDao.insert(3, 5, 3);
        ratingDao.insert(3, 6, 3);
    }
}
