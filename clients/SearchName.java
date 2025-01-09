package clients;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SearchName{
	
	private HashMap<String, String> Products() {
		HashMap<String,String> productHash = new HashMap<>();
		productHash.put("0001","40 inch LED HD TV");
		productHash.put("0002","DAB Radio");
		productHash.put("0003","Toaster");
		productHash.put("0004","Watch");
		productHash.put("0005","Digital Camera");
		productHash.put("0006","MP3 Player");
		productHash.put("0007","32Gb USB2 drive");
		return(productHash);
		
	}
	
	
	public String getNumFromName(String Name) {

		
		int highestMatch = 0;
		String highestMatchId = null;
		int currentMatch = 0;
		
		for (Map.Entry<String,String> entry : Products().entrySet()) {
			Name = Name.toLowerCase();

			String entryName = entry.getValue().toLowerCase();
			if (entryName.contains(Name)){
				return entry.getKey();
			}
			for (int charCount = 0; charCount < Math.min(Name.length(), entry.getValue().length()); charCount++) {
						
				char currChar = Name.charAt(charCount);
				if (Objects.equals(currChar, entry.getValue().charAt(charCount))) {
					currentMatch++;
				}

			}
			if (highestMatch < currentMatch){
				highestMatchId = entry.getKey();
				highestMatch = currentMatch;
			}
				
		}
		return highestMatchId;
		
	}
	
}



