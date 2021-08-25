package mx.tec.web.lab.manager;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mx.tec.web.lab.entity.Product;
import mx.tec.web.lab.entity.Sku;
import mx.tec.web.lab.repository.ProductRepository;

/**
 * The ProductManager class exists to provide logic to operate on the data sent 
 * to and from the Value Objects class and the client. This layer provides code modularity, 
 * the business logic and rules are specified. 
 *
 * @author  Karen Alicia Hernandez
 * @version 2.0
 */

@Service
public class ProductManager {
	
	/**
	 * Data access instance
	 */
	@Resource 
	ProductRepository productRepository;

	/**
	 * This method is used to retrieve all the products currently in the 'products' list. 
	 * @return a List with the type Product. 
	 */
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	/**
	 * This method is used to retrieve a product using its id.
	 * @param id of the product. 
	 * @return an object of type Product. 
	 */
	public Optional<Product> getProduct(final long id) {

		return productRepository.findById(id);
	}

	/**
	 * Retrieve an specific product based on a given product id
	 * @param pattern Pattern to search
	 * @return Optional containing a product if the product was found or empty otherwise
	 */
	public List<mx.tec.web.lab.entity.Product> getProducts(final String pattern) {
		return productRepository.findByNameLike(pattern);
	}
	
	
	/**
	 * This method is used to create a new product. The Product type attributes can be found in 
	 * the mx.tec.lab.vo Product class. 
	 * @param Product object.
	 * @return the Product that was given in the param.
	 */
	public Product addProduct(final Product newProduct) {
		for (final Sku newSku : newProduct.getChildSkus()) {
			newSku.setParentProduct(newProduct);
		}
		return productRepository.save(newProduct);
	}

	/**
	 * This method is used to delete a product given an id. 
	 * @param Product id.
	 * @return the Product that was deleted.
	 */
	public void deleteProduct(final Product existingProduct) {
		productRepository.delete(existingProduct);
	}

	/**
	 * This method is used to update an existing product. Given an id and the new attributes of 
	 * given product. 
	 * @param Product id and object.
	 * @return the Product updated.
	 */
	public void updateProduct(final long id, final Product modifiedProduct) {
		if (modifiedProduct.getId() == id) {
			for (final Sku modifiedSku : modifiedProduct.getChildSkus()) {
				modifiedSku.setParentProduct(modifiedProduct);
			}			
			
			productRepository.save(modifiedProduct);
		}
	}
}
