package util;

public class Case {
	public final int absc;
	public final int ord;
	
	public Case(int absc, int ord) {
		super();
		this.absc = absc;
		this.ord = ord;
	}

	public String toString(){
		return "absc = " + this.absc + " " + "ord = " + this.ord;
	}

}
