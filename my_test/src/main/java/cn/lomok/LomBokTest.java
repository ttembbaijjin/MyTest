package cn.lomok;

public class LomBokTest {
	
	public static void main(String[] args) {
		A a = new A();
		//a = null;
		test2(a);
	}
	
	static void test(){
		A a = new A();
		a.setId("a");
		a.setName("b");
	}
	
	static void test2(A a){
		System.out.println(a.getName());
	}

}
