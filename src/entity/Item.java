package entity;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	private String itemId;
	private String name;
	private double rating;
	private String address;
	private String city;
	private String country;
	private String state;
	private String zipcode;
	private double latitude;
	private double longtitude;
	private String description;
	private Set<String> categories;
	private String imageUrl;
	private String url;
	private String snippet;
	private String snippetUrl;
	
	public static class ItemBuilder {
		private String itemId;
		private String name;
		private double rating;
		private String address;
		private String city;
		private String country;
		private String state;
		private String zipcode;
		private double latitude;
		private double longtitude;
		private String description;
		private Set<String> categories;
		private String imageUrl;
		private String url;
		private String snippet;
		private String snippetUrl;
		public ItemBuilder setAddress(String address) {
			this.address = address;
			return this;
		}
		public ItemBuilder setItemId(String itemId) {
			this.itemId = itemId;
			return this;
		}
		public ItemBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ItemBuilder setRating(double rating) {
			this.rating = rating;
			return this;
		}
		public ItemBuilder setCity(String city) {
			this.city = city;
			return this;
		}
		public ItemBuilder setCountry(String country) {
			this.country = country;
			return this;
		}
		public ItemBuilder setState(String state) {
			this.state = state;
			return this;
		}
		public ItemBuilder setZipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}
		public ItemBuilder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}
		public ItemBuilder setLongtitude(double longtitude) {
			this.longtitude = longtitude;
			return this;
		}
		public ItemBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		public ItemBuilder setCategories(Set<String> categories) {
			this.categories = categories;
			return this;
		}
		public ItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}
		public ItemBuilder setUrl(String url) {
			this.url = url;
			return this;
		}
		public ItemBuilder setSnippet(String snippet) {
			this.snippet = snippet;
			return this;
		}
		public ItemBuilder setSnippetUrl(String snippetUrl) {
			this.snippetUrl = snippetUrl;
			return this;
		}
		
		public Item build() {
			return new Item(this);
		}
	}
	
	private Item(ItemBuilder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.rating = builder.rating;
		this.address = builder.address;
		this.city = builder.city;
		this.country = builder.country;
		this.state = builder.state;
		this.zipcode = builder.zipcode;
		this.latitude = builder.latitude;
		this.longtitude = builder.longtitude;
		this.description = builder.description;
		this.imageUrl = builder.imageUrl;
		this.url = builder.url;
		this.snippet = builder.snippet;
		this.snippetUrl = builder.snippetUrl;
		this.categories = builder.categories;	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<String> getCategories() {
		return categories;
	}
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getSnippetUrl() {
		return snippetUrl;
	}
	public void setSnippetUrl(String snippetUrl) {
		this.snippetUrl = snippetUrl;
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("item_id", itemId);
			obj.put("name", name);
			obj.put("rating", rating);
			obj.put("address", address);
			obj.put("city", city);
			obj.put("country", country);
			obj.put("state", state);
			obj.put("zipcode", zipcode);
			obj.put("latitude", latitude);
			obj.put("longtitude", longtitude);
			obj.put("description", description);
			obj.put("categories", new JSONArray(categories));
			obj.put("image_url", imageUrl);
			obj.put("url", url);
			obj.put("snippet", snippet);
			obj.put("snippet_url", snippetUrl);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
