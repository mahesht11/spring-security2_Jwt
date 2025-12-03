package com.sec.jwt.service;

import com.sec.jwt.utility.ProductDto;
import com.sec.jwt.entity.Product;
import com.sec.jwt.repository.ProductRepository;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto saveProduct(ProductDto product) {
        Product product1 = new Product();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        Product product2 = productRepository.save(product1);
        return new ProductDto(product2.getName(), product2.getPrice());
    }

    public List<ProductDto> getAllProducts() {
        List<Product> list =  productRepository.findAll();
        return list.stream().map(product -> new ProductDto(product.getName(), product.getPrice())).collect(Collectors.toList());
    }

    public ProductDto getProductsById(Long id) {
        Product product =  productRepository.findById(id).orElseThrow(() -> new
                RuntimeException("User not exist"));
        return new ProductDto(product.getName(), product.getPrice());
    }

    public String deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exist"));
        if(product!=null) {
            productRepository.deleteById(id);
            return "deleted successfully";
        }
        return "Not deleted successfully";
    }
}
