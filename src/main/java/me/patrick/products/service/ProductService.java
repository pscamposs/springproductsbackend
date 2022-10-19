package me.patrick.products.service;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.patrick.products.model.ProductModel;
import me.patrick.products.model.ResponseModel;
import me.patrick.products.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ResponseModel responseModel;

    public Iterable<ProductModel> listModels() {
        return productRepository.findAll();
    }

    public ResponseEntity<ResponseModel> remove(long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            responseModel.setMessage("Produto deletado com sucesso.");
            responseModel.setStatus("success");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
        } else {
            responseModel.setMessage("Nenhum produto com o id '" + id + "' foi encontrado.");
            responseModel.setStatus("error");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> registerOrEdit(ProductModel productModel, String action) {

        if (productModel.getName().equals("")) {
            responseModel.setMessage("O nome do produto é obrigatório.");
            responseModel.setStatus("error");

            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        } else if (productModel.getBrand().equals("")) {
            responseModel.setMessage("O nome da marca é obrigatório.");
            responseModel.setStatus("error");
            return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.BAD_REQUEST);
        } else {
            if (action.equals("register")) {
                responseModel.setMessage("Produto criado com sucesso.");
                responseModel.setStatus("success");
                productRepository.save(productModel);
                return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.CREATED);
            } else {
                responseModel.setMessage("Produto editado com sucesso.");
                responseModel.setStatus("success");
                productRepository.save(productModel);
                return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
            }
        }

    }
}
