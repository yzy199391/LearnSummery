package dataType_test;

public class testNull {
	String null_string;

	
	public String getNull_string() {
		return null_string;
	}


	public void setNull_string(String null_string) {
		this.null_string = null_string;
	}


	public void print_null_string(){
		if(null_string == null){
			System.out.println("very good it value is null!");
		}
	}
}
