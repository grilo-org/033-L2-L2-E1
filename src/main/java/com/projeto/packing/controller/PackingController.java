package com.projeto.packing.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.projeto.packing.dto.OrderDTO;
import com.projeto.packing.dto.ApiResponse;
import com.projeto.packing.service.PackingService;

@RestController
@RequestMapping("/api/embalagem")
public class PackingController {

  private final PackingService svc;
  public PackingController(PackingService svc) {
    this.svc = svc;
  }

  @PostMapping
  public ApiResponse pack(@RequestBody List<OrderDTO> orders) {
    return svc.pack(orders);
  }
}