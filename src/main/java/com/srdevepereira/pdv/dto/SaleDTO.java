package com.srdevepereira.pdv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private Long userId;

    List<ProductSaleDTO> items;

}
