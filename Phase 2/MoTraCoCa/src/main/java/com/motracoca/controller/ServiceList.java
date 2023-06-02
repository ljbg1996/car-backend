package com.motracoca.controller;

import com.motracoca.model.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

record ServiceList(List<Service> serviceList) {
}
