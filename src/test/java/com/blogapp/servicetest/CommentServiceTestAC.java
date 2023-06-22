package com.blogapp.servicetest;

public class CommentServiceTestAC {

}


/*

package com.vegetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.vegetable.entity.Product;
import com.vegetable.exception.ProductAlreadyExistException;
import com.vegetable.repository.ProductRepository;
import com.vegetable.serviceImpl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {
	
	@InjectMocks
	private Product product;

	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepoTest;
	
	@BeforeEach
	public void setUp() {
		product.setProductId(20L);
		product.setProductName("Matar");
		product.setProductType("Asian Green");
		product.setProductPrice(10d);
		product.setProductDescription("Green beans");
		product.setProductImage("Image.png");
	}
	
	@Test
	public void testAddProduct() throws ProductAlreadyExistException {
		Mockito.doReturn(product).when(productRepoTest).save(Mockito.any());
		assertEquals(product.getProductId(), productService.addProduct(product).getProductId());
		assertEquals(product.getProductName(), productService.addProduct(product).getProductName());
		assertEquals(product.getProductType(), productService.addProduct(product).getProductType());
		assertEquals(product.getProductPrice(), productService.addProduct(product).getProductPrice());
		assertEquals(product.getProductDescription(), productService.addProduct(product).getProductDescription());
		assertEquals(product.getProductImage(), productService.addProduct(product).getProductImage());
	}
	
	public void testGetAllProducts() {
		Mockito.doReturn(Stream.of(product, product).collect(Collectors.toList())).when(productRepoTest).findAll();
		assertEquals(Stream.of(product, product).collect(Collectors.toList()), productService.getAllProducts());
		assertEquals(2, productService.getAllProducts().size());
	}
}

*/