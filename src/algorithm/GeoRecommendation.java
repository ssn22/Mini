package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;

public class GeoRecommendation {
	public List<Item> recommendItems(String userId, double lat, double lon){
		DBConnection conn = DBConnectionFactory.getDBConnection();
		Set<String> favoriteItems = conn.getFavoriteItemIds(userId);
		Set<String> allCategories = new HashSet<>();
		for (String item : favoriteItems) {//haven't consider visited times
			allCategories.addAll(conn.getCategories(item));
		}
		allCategories.remove("Undefined");
		if (allCategories.isEmpty()) {
			allCategories.add("");
		}
		Set<Item> recommendedItems = new HashSet<>();
		for (String category : allCategories) {
			List<Item> items = conn.searchItems(userId, lat, lon, category);//from External api
			recommendedItems.addAll(items);
		}
		Set<String> titles = new HashSet<>();
		List<Item> filteredItems = new ArrayList<>();
		for (Item item : recommendedItems) {
			//filter visited ones and duplicate-name ones
			if(!favoriteItems.contains(item.getItemId()) && !titles.contains(item.getName())) {
				filteredItems.add(item);
				titles.add(item.getName());
			}
			
		}
		
		//perform ranking of these items based on distance
		Collections.sort(filteredItems, new Comparator<Item>(){
			@Override
			public int compare(Item item1, Item item2) {//change to different order
				double distance1 = getDistance(item1.getLatitude(),item1.getLongitude(),lat,lon);
				double distance2 = getDistance(item2.getLatitude(),item2.getLongitude(),lat,lon);
				return (int)(distance1-distance2);//return the increasing order of dist
			}
		});
		return filteredItems;
	}
	private static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double dlon = lon2 - lon1 ;
		double dlat = lat2 - lat1 ;
		double a = Math.sin(dlat/2/180 * Math.PI) * Math.sin(dlat/2/180 * Math.PI) 
		+ Math.cos(lat1/180 * Math.PI) * Math.cos(lat2/180 * Math.PI) 
		* (Math.sin(dlon/2/180 * Math.PI) * Math.sin(dlon/2/180 * Math.PI)); 
		double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) ) ;
		double R = 3961;
		return R * c; //where R is the radius of the Earth
	}
}
