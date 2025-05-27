package com.projeto.packing.dto;

import lombok.Data;
import java.util.List;

@Data
public class ProductDTO {
    private String id;
    private int height, width, length;
}