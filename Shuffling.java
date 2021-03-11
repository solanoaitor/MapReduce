import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*Classe que s'encarrega d'ordenar elements per parelles<clau, valor>, amb claus iguals*/
public class Shuffling {

	private static Shuffling shuffling = null;
	
	private List<List<KeyValue<String,Integer>>> listParelles=null;
	
	private Shuffling() {
	}
	
	/*Utilitzem el patro Singleton,
	 * ja que ens interesa guardar tots els resultats dels threads en un mateix lloc
	 * Synchronized per que nomes ens interesa que entri un thread cada vegada, no volem solapaments de resultats*/
	public static synchronized Shuffling getInstancia() {
		if(shuffling == null) {
			shuffling = new Shuffling();
		}
		return shuffling;
	}
	
	/*Afegeix parelles de clau-valor a una llista
	 * la llista esta formada de llistes amb la mateixa clau*/
	public synchronized void addListParelles(List<KeyValue<String,Integer>> listKeyValueAdd) {
		if(listParelles == null) {
			listParelles = new ArrayList<>();
		}
		
		/*Per cada Parella que volem inserir comprovem si aquella clau esta inserida o no
		 * Si esta inserida simplement la inserim a la llista existent
		 * Si no, la inserim a la una nova llista*/
		Boolean trobat=false;
		for(KeyValue<String,Integer> element : listKeyValueAdd) {
			
			/*utilitzem iteradors perque es la forma mes rapida de saltar un while si trovem que la clau existeix*/
			Iterator<List<KeyValue<String,Integer>>> iterListParellesActual = listParelles.iterator();
			
			/*Per cada iteracio, 
			 * comprovem si la clau de la parella que volem inserir existeix en alguna llista*/
			while((iterListParellesActual.hasNext()) && (!trobat)) {
				List<KeyValue<String,Integer>> aux1 = iterListParellesActual.next();
				KeyValue<String,Integer> parellaAux = aux1.iterator().next(); //nomes ens interesa comprovar el primer element de la llista, els altres seran els mateixos
				
				/*si la clau ja la ten�em, l'afegim a la llista*/
				if(parellaAux.getKey().equals(element.getKey())) {
					aux1.add(element);
					trobat=true;
				}
			}
			
			/*si la paraula no la ten�em, l'afegim en una nova llista*/
			if(!trobat) {
				List<KeyValue<String,Integer>> listAux = new ArrayList<>();
				listAux.add(element);
				listParelles.add(listAux);
			}
			trobat=false;
		}			
	}
	
	public List<List<KeyValue<String,Integer>>> getList() {
		return listParelles;
	}
	
	public void initSuffling() {
		shuffling=null;
		listParelles=null;
	}
}
