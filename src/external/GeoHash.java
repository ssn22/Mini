package external;

public class GeoHash {
//convert coordinates to a string
	    private static final String BASE_32 = "0123456789bcdefghjkmnpqrstuvwxyz";

	    private static int divideRangeByValue(double value, double[] range) {
	        double mid = middle(range);
	        if (value >= mid) {
	            range[0] = mid;
	            return 1;
	        } else {
	            range[1] = mid;
	            return 0;
	        }
	    }

	    private static void divideRangeByBit(int bit, double[] range) {
	        double mid = middle(range);
	        if (bit > 0) {
	            range[0] = mid;
	        } else {
	            range[1] = mid;
	        }
	    }

	    private static double middle(double[] range) {
	        return (range[0] + range[1]) / 2;
	    }

	    public static String encodeGeohash(double latitude, double longitude, int precision) {
	        double[] latRange = new double[]{-90.0, 90.0};
	        double[] lonRange = new double[]{-180.0, 180.0};
	        boolean isEven = true;
	        int bit = 0;
	        int base32CharIndex = 0;
	        StringBuilder geohash = new StringBuilder();

	        while (geohash.length() < precision) {
	            if (isEven) {
	                base32CharIndex = (base32CharIndex << 1) | divideRangeByValue(longitude, lonRange);
	            } else {
	                base32CharIndex = (base32CharIndex << 1) | divideRangeByValue(latitude, latRange);
	            }

	            isEven = !isEven;

	            if (bit < 4) {
	                bit++;
	            } else {
	                geohash.append(BASE_32.charAt(base32CharIndex));
	                bit = 0;
	                base32CharIndex = 0;
	            }
	        }

	        return geohash.toString();
	    }

	    public static double[] decodeGeohash(String geohash) {
	        double[] latRange = new double[]{-90.0, 90.0};
	        double[] lonRange = new double[]{-180.0, 180.0};
	        boolean isEvenBit = true;

	        for (int i = 0; i < geohash.length(); i++) {
	            int base32CharIndex = BASE_32.indexOf(geohash.charAt(i));
	            for (int j = 4; j >= 0; j--) {
	                if (isEvenBit) {
	                    divideRangeByBit((base32CharIndex >> j) & 1, lonRange);
	                } else {
	                    divideRangeByBit((base32CharIndex >> j) & 1, latRange);
	                }
	                isEvenBit = !isEvenBit;
	            }
	        }

	        return new double[]{middle(latRange), middle(lonRange)};
	    }
	    
	    public static void main(String[] args) {
	    	System.out.println(encodeGeohash(57.64911, 10.40744, 12));
	    	return;
	    }
}
	//assertEquals("u4pruydqqvj8", encodeGeohash(57.64911, 10.40744, 12));
	//assertArrayEquals(new double[] {57.64911, 10.40744}, decodeGeohash("u4pruydqqvj8"), 0.000001);

