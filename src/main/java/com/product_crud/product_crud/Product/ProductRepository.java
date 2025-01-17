package com.product_crud.product_crud.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface ProductRepository  extends JpaRepository<Product,Long>{
	
	

}
