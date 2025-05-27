package com.projeto.packing.dto;

import lombok.Data;
import java.util.List;
@Data
public class PackingResponse {
    private String orderId;
    private List<BoxDTO> boxes;
}