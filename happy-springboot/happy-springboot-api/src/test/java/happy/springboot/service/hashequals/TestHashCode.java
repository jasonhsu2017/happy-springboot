package happy.springboot.service.hashequals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestHashCode {

	public static void main(String[] args) {
		Person p = new Person("张三", 12);
		Person p2 = new Person("张三", 12);
		System.err.println("==:"+(p == p2));
		System.err.println("equals:"+(p.equals(p2)));
		//重写equals
		HashSet<Person> pSet = new HashSet<Person>();
		pSet.add(p);
		pSet.add(p2);
		System.err.println("pSet: "+pSet.size());		
		
		List<Person> plist=new ArrayList<Person>();
		plist.add(p);
		plist.add(p2);
		System.err.println("plist: "+plist.size());
	}

}
