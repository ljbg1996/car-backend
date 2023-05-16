package com.motracoca.motracoca.model;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public class  Product {

    private final long id;

    private final ArticleNumber articleNumber;

    private final Price price;

    private final List<Service> includedServices;




}




