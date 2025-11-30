package com.mente.productosMente.service;

import com.mente.productosMente.model.Product;
import com.mente.productosMente.repository.ProductRepository;
import com.mente.productosMente.service.interfaces.IProductService;
import com.mente.productosMente.utils.dto.excepcions.IdNotFoundException;
import com.mente.productosMente.utils.dto.request.ProductRequest;
import com.mente.productosMente.utils.dto.response.ProductResponse;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private final ProductRepository productRepository;


    @Override
    public ProductResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public void delete(Long id) {
        Product product = this.find(id);
        this.productRepository.delete(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        // crea un producto vacio y llama al request para traer los datos del cliente
        Product product = this.requestToProduct(request, new Product());
        // inserta el registro en la tabla product y retorna la entidad en un dto de respuesta pesando para el cliente
        return this.entityToResponse(this.productRepository.save(product));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        // Buscar el producto por el id
        Product product = this.find(id);
        // llenar el producto con los datos nuevos
        Product productUpdate = this.requestToProduct(request, product);
        // guardamos el producto actualizado y se convierte en respuesta
        return this.entityToResponse(this.productRepository.save(productUpdate));
    }

    @Override
    public Page<ProductResponse> getAll(int page, int size) {
        // no puede haber paginas negativas solo superiores a 0
        if(page < 0){
            page = 0;
        }
        // configuracion para el numero de paginas y elementos que devuelva
        PageRequest pagination = PageRequest.of(page, size);
        // obtiene todos los elementos paginados desde la bd y luego lo convierte en product response
        return this.productRepository.findAll(pagination)
        .map(this::entityToResponse); // ejecuta el entity response por cada elemente con operador de referencia
    }


    private Product find(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Product"));
    }
    // para devolver solo los datos necesarios y no los que devuelva la bd o datos sensibles
    private ProductResponse entityToResponse(Product product) {
        // Crear la instancia de la respuesta
        ProductResponse response = new ProductResponse();

        // Copiamos las propiedades de de la entidad al dto de respuesta
        BeanUtils.copyProperties(product, response);

        return response;
    }
    // Convierte el DTO que envia el cliente a una entidad lista para guardar
    private Product requestToProduct(ProductRequest request, Product product) {
        // Hacemos la copia
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return product;
    }

}
