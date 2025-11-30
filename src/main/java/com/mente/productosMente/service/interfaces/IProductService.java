package com.mente.productosMente.service.interfaces;

import com.mente.productosMente.utils.dto.request.ProductRequest;
import com.mente.productosMente.utils.dto.response.ProductResponse;

public interface IProductService extends CrudService<ProductRequest, ProductResponse, Long>{
    public ProductResponse getById(Long id);
}
