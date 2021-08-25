package mx.tec.web.lab.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
* The Product Entity class uses the Value Objects pattern. This pattern does just that, 
* transform our values into objects. 
*
* @author  Karen Alicia Hernandez
* @version 2.0
*/

@Entity
public class Product implements Serializable {

	/**
	 * This number called serialVersionUID, is used during deserialization to verify 
	 * that the sender and receiver of a serialized object have loaded classes for 
	 * that object that are compatible with respect to serialization.
	 */
	private static final long serialVersionUID = 1940424686731950240L;
	
	/** Product id */
	@Id
	@GeneratedValue
	private long id;
	
	/** Product name */
	private String name;
	
	/** Product description */
	private String description;

	/** Product child SKUs */
	@OneToMany(mappedBy = "parentProduct", cascade = CascadeType.ALL)
	private List<Sku> childSkus;
	
	/**
	 * A Product has several attributes, like id, name, description and finally childSkus. 
	 * This last attribute is a List, since a Product can have more than 1 Sku. 
	 */
	public Product() {
	}
	
	/**
	 * Constructor including all the properties
	 * @param id
	 * @param name
	 * @param description
	 * @param childSkus
	 */
	public Product(long id, String name, String description, List<Sku> childSkus) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.childSkus = childSkus;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the childSkus
	 */
	public List<Sku> getChildSkus() {
		return childSkus;
	}
	/**
	 * @param childSkus the childSkus to set
	 */
	public void setChildSkus(List<Sku> childSkus) {
		this.childSkus = childSkus;
	}

	/**
	 * Create a string with the Product properties
	 * @return A string with all the attributes of the Product, concatenating them.
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", childSkus=" + childSkus
				+ "]";
	}

	/**
	 * Calculate the hashcode using the id
	 * @return a hash value of a product based on the id
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Calculate the equality using the id
	 * @param an object to be compared 
	 * @return a boolean value whether the object is equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		return id == other.id;
	}
}
