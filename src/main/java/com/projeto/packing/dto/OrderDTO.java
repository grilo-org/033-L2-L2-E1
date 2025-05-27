package com.projeto.packing.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;
    private List<ProductDTO> products;
}