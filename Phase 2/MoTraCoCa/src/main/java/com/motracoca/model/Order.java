package com.motracoca.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Order {


    private final long id;

    private final boolean isPayed;

    private final LocalDate paymentDate;

    private final Vehicle vehicle;

    private final Customer customer;

    private final Price totalPrice;

    private final LocalDate date;

    private final List<ProductConfiguration> products;

    private final boolean isCanceled;

    private final LocalDate cancellationDate;

}


