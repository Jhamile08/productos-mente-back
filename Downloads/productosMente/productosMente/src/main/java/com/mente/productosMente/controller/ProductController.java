package com.mente.productosMente.controller;

import com.mente.productosMente.service.interfaces.IProductService;
import com.mente.productosMente.utils.dto.errors.ErrorResponse;
import com.mente.productosMente.utils.dto.request.ProductRequest;
import com.mente.productosMente.utils.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController // indica que recibe peticiones http
@RequestMapping(path = "/product") // define la ruta base
@AllArgsConstructor
// Con Tag podemos cambiar el nombre en swagger
@Tag(name = "Productos")
public class ProductController {

    @Autowired
    private final IProductService productService;

    @Operation(summary = "Lista todos los productos con paginación", description = "Debes enviar la pagina y el tamaño de la pagina para recibir todos los productos correspondientes")
    @GetMapping // indica que este metodo responde una peticion get
    public ResponseEntity<Page<ProductResponse>> get(
            @RequestParam(defaultValue = "1") int page, // obtener parametros de la url luego del ?, como query params
            @RequestParam(defaultValue = "2") int size){
        return ResponseEntity.ok(this.productService.getAll(page - 1, size));
    }

    // ApiResponse nos ayuda a crear un nuevo esquema de respuesta
    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @Operation(summary = "Lista un producto por id", description = "Debes enviar el id del producto a buscar")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> getById(
            @PathVariable Long id) { // Extrae el valor desde la ruta url
        return ResponseEntity.ok(this.productService.getById(id));
    }

    @Operation(summary = "Crea un producto", description = "Crea un producto")
    @PostMapping
    public ResponseEntity<ProductResponse> insert(
            @Validated @RequestBody ProductRequest product){
        return ResponseEntity.ok(this.productService.create(product));
    }

    @Operation(summary = "Elimina un producto por Id", description = "Elimina un producto por Id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualiza un producto", description = "Actualiza un producto")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id, // id por url
            @Validated @RequestBody ProductRequest product // producto actualizada
    ) {
        return ResponseEntity.ok(this.productService.update(id, product));
    }

}
