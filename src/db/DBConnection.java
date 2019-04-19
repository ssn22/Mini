package db;

import java.util.List;
import java.util.Set;

import entity.Item;

public interface DBConnection {
	public void close();//close the connection
	//insert items for a user
	public void setFavoriteItems(String userId, List<String> itemIds);
	//delete items for a user
	public void unsetFavoriteItems(String userId, List<String> itemIds);
	
	public Set<String> getFavoriteItemIds(String userId);
	
	public Set<Item> getFavoriteItems(String userId);
	
	public Set<String> getCategories(String itemId);
	//search items near a geolocation and a term(optional)
	public List<Item> searchItems(String userId, double lat, double lon, String term);
	public void saveItem(Item item);// save to database
	public String getFullname(String userId);
	public boolean verifyLogin(String userId, String password);
}
