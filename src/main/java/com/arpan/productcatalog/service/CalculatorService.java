package com.arpan.productcatalog.service;

import org.springframework.stereotype.Component;

@Component
public class CalculatorService {

    public int doSum(int x, int y) {
        return x + y;
    }
}
