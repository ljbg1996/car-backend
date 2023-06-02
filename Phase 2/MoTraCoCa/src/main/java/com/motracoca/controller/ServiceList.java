package com.motracoca.controller;

import com.motracoca.model.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
class ServiceList {
    public List<Service> serviceList;
}
