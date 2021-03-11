import java.util.ArrayList;
import java.util.List;

/*Classe que s'encarrega de transformar el string del conjunt de paraules a una llista de clau-valor*/
public class Mapping{

	public Mapping(String paragraf) {
		doMapping(paragraf);
	}
	
	public void doMapping(String paragraf) {
		/*Separem cada paraula en un array de Strings*/
		String[] clauValor = paragraf.split(" ");
		
		List<KeyValue<String,Integer>> llistaParelles = new ArrayList<>();
		
		/*Per cada paraula del array String, es crea una parella de clau-valor,
		 * inicialment el valor de cada clau sera 1*/
		for(String clau:clauValor) {
			KeyValue<String,Integer> parella = new KeyValue<String, Integer>(clau,1);
			llistaParelles.add(parella);
		}
		
		/*Un cop creada creada la llista, l'enviem per parametre al Shuffling,
		 * que s'encarregara de separar-les para claus
		 * Shuffling es singleton*/
		Shuffling shuffling = Shuffling.getInstancia();
		shuffling.addListParelles(llistaParelles);
	}
}
