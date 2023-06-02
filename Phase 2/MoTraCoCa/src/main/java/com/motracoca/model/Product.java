package com.motracoca.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class  Product {

    private final long id;

    private final ArticleNumber articleNumber;

    private final Price price;

    private final List<Service> includedServices;




}




