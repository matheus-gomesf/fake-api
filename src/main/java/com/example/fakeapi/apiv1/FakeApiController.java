package com.example.fakeapi.apiv1;

import com.example.fakeapi.apiv1.dto.ProductsDTO;
import com.example.fakeapi.business.FakeApiService;
import com.example.fakeapi.business.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    private final FakeApiService service;
    private final ProductService productService;

    @Operation(summary = "Search API products and Save", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search performed successfully"),
            @ApiResponse(responseCode = "500", description = "Error when performing data search"),
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductsDTO>> saveFromApi(){
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Save new products", method ="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product saved successfully"),
            @ApiResponse(responseCode = "500", description = "Error saving products"),
    })
    @PostMapping
    public ResponseEntity<ProductsDTO> saveProducts(@RequestBody ProductsDTO dto){

        return ResponseEntity.ok(productService.save(dto));
    }

    @Operation(summary = "Update products", method ="PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "500", description = "Error when updating products"),
    })
    @PutMapping
    public ResponseEntity<ProductsDTO> updateProducts(@RequestParam("id") String id, @RequestBody ProductsDTO dto){

        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @Operation(summary = "Delete products", method ="DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Error deleting products"),
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteProducts(@RequestParam("name") String nome){
        productService.deleteProduct(nome);
        return ResponseEntity.accepted().build();
    }


    @Operation(summary = "Search all products", method ="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search performed successfully"),
            @ApiResponse(responseCode = "500", description = "Error when performing data search"),
    })
    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAll(){

        return ResponseEntity.ok(productService.getAll());
    }
}
