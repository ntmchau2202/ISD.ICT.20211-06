package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	  private List<Bike> lstCartMedia;
	  private static final Cart cartInstance;

	  /**
	   * This is the method to get or create new cart instance.
	   * <br>@return Cart
	   */
	  public static Cart getCart() {
	    //    if (cartInstance == null) {
	    //      cartInstance = new Cart();
	    //    }
	    return cartInstance;
	  }

	  private Cart() {
	    lstCartMedia = new ArrayList<>();
	  }
	  
	  static {
	    try {
	      cartInstance = new Cart();
	    } catch (Exception e) {
	      throw new RuntimeException("Exception occured in creating singleton instance");
	    }
	  }

	  public void addBike(Bike cm) {
	    lstCartMedia.add(cm);
	  }
	  public List<Bike> getListMedia() {
	    return lstCartMedia;
	  }

	  public void emptyCart() {
	    lstCartMedia.clear();
	  }

	}