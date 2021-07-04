package com.pranoy.spring.security.controllers;

import com.pranoy.spring.security.dto.Coupon;
import com.pranoy.spring.security.model.Product;
import com.pranoy.spring.security.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/productapi")
public class ProductRestController  {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceUrl;

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        Coupon coupon =
                restTemplate.getForObject(couponServiceUrl + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepository.save(product);
    }
}
