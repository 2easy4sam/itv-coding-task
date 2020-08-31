package com.itv.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itv.dao.*;
import com.itv.seeder.DataSeeder;
import com.itv.service.DefaultPYOService;
import com.itv.service.PYOService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteDataSource;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Value("${spring.datasource.url}") String url) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AdvertiserDao advertiserDao(JdbcTemplate jdbcTemplate) {
        return new AdvertiserDao(jdbcTemplate);
    }

    @Bean
    public BreakDao breakDao(JdbcTemplate jdbcTemplate) {
        return new BreakDao(jdbcTemplate);
    }

    @Bean
    public CampaignDao campaignDao(JdbcTemplate jdbcTemplate) {
        return new CampaignDao(jdbcTemplate);
    }

    @Bean
    public DemographicProfileDao demographicProfileDao(JdbcTemplate jdbcTemplate) {
        return new DemographicProfileDao(jdbcTemplate);
    }

    @Bean
    public PYORequestDao pyoRequestDao(JdbcTemplate jdbcTemplate) {
        return new PYORequestDao(jdbcTemplate);
    }

    @Bean
    public RatingDao ratingDao(JdbcTemplate jdbcTemplate) {
        return new RatingDao(jdbcTemplate);
    }

    @Bean
    public DataSeeder dataSeeder(AdvertiserDao advertiserDao,
                                 BreakDao breakDao,
                                 CampaignDao campaignDao,
                                 DemographicProfileDao demographicProfileDao,
                                 PYORequestDao pyoRequestDao,
                                 RatingDao ratingDao) {
        return new DataSeeder(advertiserDao, breakDao, campaignDao, demographicProfileDao, pyoRequestDao, ratingDao);
    }

    @Bean
    public PYOService pyoService(AdvertiserDao advertiserDao,
                                 BreakDao breakDao,
                                 CampaignDao campaignDao,
                                 DemographicProfileDao demographicProfileDao,
                                 PYORequestDao pyoRequestDao,
                                 RatingDao ratingDao) {
        return new DefaultPYOService(advertiserDao, breakDao, campaignDao, demographicProfileDao, pyoRequestDao, ratingDao);
    }
}
