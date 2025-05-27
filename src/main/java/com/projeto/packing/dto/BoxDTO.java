package com.projeto.packing.dto;

import lombok.Data;
import java.util.List;
@Data
public class BoxDTO {
    private String boxType;
    private List<String> productIds;

}