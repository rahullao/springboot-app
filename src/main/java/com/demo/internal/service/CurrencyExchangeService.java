package com.demo.internal.service;

import com.demo.internal.domain.ExchangeValue;
import com.demo.internal.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyExchangeService.class);

    @Autowired
    private ExchangeValueRepository repository;

    public ExchangeValue getExchangeValue(final String from, final String to) {
        LOG.info("Getting exchange value from='{}' to='{}'", from, to);
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        if (exchangeValue == null) {
            throw new RuntimeException("Unable to find data to convert " + from + " to " + to);
        }
        LOG.info("Exchange value '{}' to '{}' is '{}'", from, to, exchangeValue);
        return exchangeValue;
    }
}
