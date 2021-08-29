package com.demo.internal.service;

import com.demo.internal.domain.ExchangeValue;
import com.demo.internal.repository.ExchangeValueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeServiceTest {

    @Mock private ExchangeValueRepository repository;

    @InjectMocks
    private CurrencyExchangeService underTest;

    @Test
    public void getExchangeValue() {
        String from = "EUR", to = "INR";
        ExchangeValue exchangeValue = new ExchangeValue(1L, from, to, new BigDecimal(85));
        Mockito.when(repository.findByFromAndTo(anyString(), anyString()))
                .thenReturn(exchangeValue);
        ExchangeValue value = underTest.getExchangeValue(from, to);
        assertThat(value.getConversionMultiple().intValue(), is(85));
    }
}