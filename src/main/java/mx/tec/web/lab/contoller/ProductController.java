/**
 * 
 */
package mx.tec.web.lab.contoller;


import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.web.lab.entity.Product;
import mx.tec.web.lab.manager.ProductManager;

/**
 * The ProductController class is a RestController annotation which is used to create RESTful 
 * web services using Spring MVC. Spring RestController takes care of mapping request data to 
 * the defined request handler method. Once response body is generated from the handler method, 
 * it converts it to JSON or response.
 *
 * @author  Karen Alicia Hernandez
 * @version 2.0
 */

@RestController
public class ProductController {
	
	/**
	 * Resource handling business logic
	 */
	@Resource
	private ProductManager productManager;
	
	/**
	 * This method creates an end point to get all the products.
	 * @return a List of all the Products.
	 */
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productManager.getProducts();
	
		return new ResponseEntity<>(products, HttpStatus.OK);
	} 
	
	/**
	 * This method creates an end point to get a product by its id.
	 * @param id of the Product to get.
	 * @return a List of all the Products.
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(value = "id") long id) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		Optional<Product> product = productManager.getProduct(id);
		
		if (product.isPresent()) {
			responseEntity = new ResponseEntity<>(product.get(), HttpStatus.OK);
		}
		
		return responseEntity;
	}	
	
	/**
	 * The end point for GET {url}/products?search={pattern}
	 * @param search Pattern to search
	 * @return a json list of all the products matching the pattern
	 */
	@GetMapping(value="/products", params="search")
	public ResponseEntity<List<Product>> getProducts(@RequestParam String search) {
		List<Product> products = productManager.getProducts(search);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	/**
	 * This method creates an end point to create a new product.
	 * @param newProduct, a JSON with the Product properties. 
	 * @return the Product created. 
	 */
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
		Product product = productManager.addProduct(newProduct);

		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	/**
	 * This method creates an end point to delete a product by its id.
	 * @param id of the Product to delete.
	 * @return the Product deleted. 
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") long id) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Optional<Product> product = productManager.getProduct(id);
		
		if (product.isPresent()) {
			productManager.deleteProduct(product.get());
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		
		return responseEntity;
	}
	/**
	 * This method creates an end point to update an existing product by its id.
	 * @param id of the Product to update.
	 * @param modifiedProduct new updated product 
	 * @return the Product updated. 
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") long id, @RequestBody Product modifiedProduct) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		Optional<Product> product = productManager.getProduct(id);
		
		if (product.isPresent()) {
			productManager.updateProduct(id, modifiedProduct);
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}
		
		return responseEntity;
	}
	
	
}
