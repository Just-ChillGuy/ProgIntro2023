public class Sum{
	public static void main(String[] mystring) {
		int sum_mystring = 0;
		int cntdown = 0;
		for (int i = 0; i < mystring.length; i++){
			for (int y = 0; y < mystring[i].length(); y++){
				if (Character.isWhitespace(mystring[i].charAt(y)) == false){
					cntdown = y;
					//System.out.println("in");
					while ((Character.isWhitespace(mystring[i].charAt(y)) == false)){
						if (y + 1 == mystring[i].length()){
							//System.out.println("in");
							sum_mystring += Integer.parseInt(mystring[i].substring(cntdown, y + 1));
							break;
						} else {
							y += 1;
						}
					}
					if (Character.isWhitespace(mystring[i].charAt(y))){
						sum_mystring += Integer.parseInt(mystring[i].substring(cntdown, y));
					}				
				}
			}
		}
		System.out.println(sum_mystring);
	}
}