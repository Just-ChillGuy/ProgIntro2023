public class SumDouble {
	public static void main(String[] mystring) {
		double sum_mystring = 0;
		int cntdown = 0;
		for (int i = 0; i < mystring.length; i++){
			for (int y = 0; y < mystring[i].length(); y++){
				if (Character.isWhitespace(mystring[i].charAt(y)) == false){
					cntdown = y;
					while ((Character.isWhitespace(mystring[i].charAt(y)) == false)){
						if (y + 1 == mystring[i].length()){
							sum_mystring += Double.parseDouble(mystring[i].substring(cntdown, y + 1));
							break;
						} else {
							y ++;
						}
					}
					if (Character.isWhitespace(mystring[i].charAt(y))){
						sum_mystring += Double.parseDouble(mystring[i].substring(cntdown, y));
					}				
				}
			}
		}
		System.out.println(sum_mystring);
	}
}
