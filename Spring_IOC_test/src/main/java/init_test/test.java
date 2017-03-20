package init_test;

public class test {
	public test(String str_ref,int int_ref){
		System.out.println("在public test构造方法中打印");
		System.out.println(str_ref + " " + int_ref);
	}
	
	public test(String str1_ref,String str2_ref){
		System.out.println("在public test2构造方法中打印");
		System.out.println(str1_ref + " " + str2_ref);
	}
}
