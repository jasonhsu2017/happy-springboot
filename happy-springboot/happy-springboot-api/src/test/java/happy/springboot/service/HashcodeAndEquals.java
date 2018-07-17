package happy.springboot.service;

public class HashcodeAndEquals {

	
	
	public static void main(String[] args) {
	
		
		System.err.println(new Integer(1).hashCode());
		System.err.println(new Integer(1).hashCode());		
		System.err.println(new Integer(1).equals(new Integer(1)));
		System.err.println(new Integer(999)==999);
		
  
	}
}
