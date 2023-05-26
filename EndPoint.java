package leaseplan.common;

public enum EndPoint{
	
	SEARCH("/search/demo/");
	
	private final String pathFragment;
	EndPoint(String pathFragment){
		this.pathFragment=pathFragment;
	}
	
	 @Override
	    public String toString() {
	    return this.pathFragment;
	   }
	
	public static String lookUpFromString(String path) {
	for(EndPoint endpoint:EndPoint.values()) {
		if(endpoint.pathFragment.equalsIgnoreCase(path)) {
			return endpoint.toString();
		}
	}
		return null;
}
}
