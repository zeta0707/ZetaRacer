
public class SessionKeyGenerator {

	public static String generate(){
		String key = "";
		int a = (int)(Math.random()*2);
		int b = (int)(Math.random()*2);
		int c = (int)(Math.random()*2);
		int d = (int)(Math.random()*2);
		int e = (int)(Math.random()*4);
		if(e==0)
			key = a+""+c+""+b+""+d;
		else if(e==1)
			key = a+""+d+""+b+""+c;
		else if(e==2)
			key = a+""+c+""+b+""+d;
		else
			key = a+""+d+""+c+""+b;
		return jumble(key);
	}
	
	private static String jumble(String original)
	  {
	    StringBuilder sb = new StringBuilder(original);
	 
	    char temp;
	    int  swapWith;
	    for(int i=0; i<sb.length(); i++)
	    {
	      temp = sb.charAt(i);
	      swapWith = (int)Math.floor(Math.random()*sb.length());
	      sb.setCharAt(i, sb.charAt(swapWith));
	      sb.setCharAt(swapWith, temp);
	    }
	 
	    return sb.toString();
	  }
	
	public static void main(String[] args) {
		while(true)
		System.out.println(generate());
	}
	
}
