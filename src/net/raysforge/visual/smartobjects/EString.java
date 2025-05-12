package net.raysforge.visual.smartobjects;

public class EString {
	
	String s;
	
	public EString() {
	}
	
	public EString(String s) {
		this.s = s;
	}

	public void init( String s)
	{
		this.s = s;
	}
	
	public EString substring( int start, int end)
	{
		return new EString (s.substring( start, end<0?s.length()+end:end));
	}

}
