
public class RandomMethode {
	// Er moet iets van een methode komen die de laatste rij van de arraylist meegeeft 
	
	//hiermee moet worden berekend op welke vakken een gat moet komen 
	public static void main(String[] args) {
		//de allereerste rij die wordt aangemaakt
		String rij = "00100";
		//een for lus die de methode 15 keer doorloopt, dit is voor om te zien wat die doet
		//iedere keer wordt de String rij opnieuwe gevuld met de random gemaakte string
		for (int i = 0; i < 15; i++) {
			rij = methode(rij);
		}

	}
	
	/**
	 * Methode die ervoor zorgt dat zowel links of rechts een gat komt, ook als hij helemaal rechts zit of helemaal links
	 * @param getLaatsteRij		--De laatste rij van de arralist die wordt meegegeven 
	 * @return String nieuweRij	--Hij returnt de nieuwe random gemaakte string
	 */
	public static String methode(String getLaatsteRij){
		//Om de meegegven rij te kunnen zien
		System.out.println(getLaatsteRij);
		//De variabele die de plaats van het gat omhoudt 
		int plaatsGat = 0;
		//hij loopt de hele meegegeven string door en geef de paats van het gat mee aan de variabele plaatsGat
		for (int i = 0; i < 5; i++) {
			if (getLaatsteRij.charAt(i) == '1'){
				plaatsGat = i;
				System.out.println(plaatsGat);
			}
		}
		//De variabele voor het nieuwe gat
		int nieuwGat = 0;
		//Genereerd 1 of 0
		int r = (int) (Math.random()*2);
		System.out.println(r);
		//als r 0 is dan komt het gat 1 naar links
		if (r == 0){
			nieuwGat = plaatsGat-1;
		}
		//als r 1 is en de plaats van het gat van de vorige rij 4 is(helemaal rechts) dan is het nieuwe gat op plaats 0
		else if(r == 1&&plaatsGat == 4){
			nieuwGat = 0;
		}
		//als r 0 is en de plaats van het gat van de vorige rij 0(helemaal links) dan is het nieuwe gat op plaats 4
		else if(r==0&&plaatsGat == 0){
			nieuwGat = 4;
		}
		//als dat allemaal niet waar is gaat het gat 1 naar links
		else{
			nieuwGat = plaatsGat + 1;
		}
		System.out.println(nieuwGat);
		String nieuwerij = "";
		// de nieuwerij wordt aangemaakt en gereturnt 
		for(int i1 = 0; i1<5; i1++){
			if (i1 == nieuwGat){
				nieuwerij = nieuwerij + "1";
			} else{
				nieuwerij = nieuwerij + "0";
			}
		}
		System.out.println(nieuwerij);
		return nieuwerij;
	}
	
}
