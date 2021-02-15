package dataAccess;

import java.util.HashSet;

/*
 * This is the cache that holds all the token of the devices
 */
public class DataStoreCache {
	private static HashSet<String> tokenCache = new HashSet<String>();
	@SuppressWarnings("unused")
	private static DataStoreCache instance = new DataStoreCache();
	
	private DataStoreCache() {
		tokenCache.add("abcd+-");
		tokenCache.add("1234+-");
		tokenCache.add("whatIsThis");
	}
	
	public static boolean isTokenAccepted(String token) {
		if (tokenCache.contains(token)) {
			return true;
		}
		
		return false;
	}
}
