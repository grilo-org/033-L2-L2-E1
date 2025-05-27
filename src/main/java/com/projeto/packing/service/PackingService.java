package com.projeto.packing.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.projeto.packing.dto.ApiResponse;
import com.projeto.packing.dto.OrderDTO;
import com.projeto.packing.dto.BoxDTO;
import com.projeto.packing.dto.PackingResponse;
import com.projeto.packing.dto.ProductDTO;
import com.projeto.packing.model.BoxType;

@Service
public class PackingService {
  public ApiResponse pack(List<OrderDTO> orders) {
    ApiResponse resp = new ApiResponse();
    resp.setResults(new ArrayList<>());

    for (OrderDTO order : orders) {
      List<BoxDTO> used = new ArrayList<>();

      // ordena por volume decrescente
      order.getProducts()
           .sort((a,b) -> Integer.compare(b.getHeight()*b.getWidth()*b.getLength(),
                                           a.getHeight()*a.getWidth()*a.getLength()));

      for (ProductDTO p : order.getProducts()) {
        boolean placed = false;
        for (BoxDTO box : used) {
          if (fits(box, p)) {
            box.getProductIds().add(p.getId());
            placed = true;
            break;
          }
        }
        if (!placed) {
          // abre nova caixa: escolhe a menor que cabe
          BoxType escolha = Arrays.stream(BoxType.values())
            .filter(bt -> canFit(bt, p))
            .min(Comparator.comparing(bt -> bt.h*bt.w*bt.l))
            .orElseThrow(() -> new RuntimeException("Produto não cabe em nenhuma caixa"));
          BoxDTO nova = new BoxDTO();
          nova.setBoxType(escolha.name());
          nova.setProductIds(new ArrayList<>(List.of(p.getId())));
          used.add(nova);
        }
      }

      PackingResponse pr = new PackingResponse();
      pr.setOrderId(order.getOrderId());
      pr.setBoxes(used);
      resp.getResults().add(pr);
    }

    return resp;
  }

  private boolean canFit(BoxType bt, ProductDTO p) {
    // tenta todas as rotações 3! = 6
    int[] dims = {p.getHeight(), p.getWidth(), p.getLength()};
    for (int i=0;i<6;i++) {
      int h = dims[0], w = dims[1], l = dims[2];
      if (h<=bt.h && w<=bt.w && l<=bt.l) return true;
      // permutações simples
      rotate(dims);
    }
    return false;
  }

  private void rotate(int[] d) {
    // gira: [h,w,l] → [w,l,h]
    int tmp = d[0]; d[0]=d[1]; d[1]=d[2]; d[2]=tmp;
  }

  private boolean fits(BoxDTO box, ProductDTO p) {
    // pra simplificar, só checa se cabe no tipo da caixa
    return canFit(BoxType.valueOf(box.getBoxType()), p);
  }
}
