package com.itv.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.sqlite.SQLiteDataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
public class AdvertiserDaoIT {
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    private JdbcTemplate jdbcTemplate;
    private AdvertiserDao advertiserDao;

    @Before
    public void setUp() {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(dataSourceUrl);
        jdbcTemplate = new JdbcTemplate(sqLiteDataSource);

        advertiserDao = new AdvertiserDao(jdbcTemplate);
        advertiserDao.createTable();
    }

    @Test
    public void insert_withSuccess() {
        String advertiserName = "ITV";
        advertiserDao.insert(advertiserName);

        assertThat(jdbcTemplate.query("SELECT count(*) FROM advertiser WHERE name = '" + advertiserName + "'", (rs, rowNum) -> rs))
                .hasSize(1);
    }
}