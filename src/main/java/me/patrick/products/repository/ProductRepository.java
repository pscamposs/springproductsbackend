package me.patrick.products.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.patrick.products.model.ProductModel;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Long> {

}
