package com.demo.internal.repository;

import com.demo.internal.domain.ExchangeValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeValueRepositoryTest {

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @Before
    public void setUp() {
        exchangeValueRepository.deleteAll();
        exchangeValueRepository.save(new ExchangeValue(1L, "EUR",
                "INR", new BigDecimal(89)));
        exchangeValueRepository.save(new ExchangeValue(2L, "USD",
                "INR", new BigDecimal(75)));
    }

    @Test
    public void findByFromAndTo() {
        assertThat(exchangeValueRepository.findAll().size(), is(2));
        ExchangeValue value = exchangeValueRepository.findByFromAndTo("EUR", "INR");
        assertThat(value.getConversionMultiple().intValue(), is(89));
    }
}