import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*Classe que s'encarrega de rebre les parelles resultants del Reducing i mostrar el resultat*/
public class ResultatFinal {

	private static ResultatFinal resultatFinal= null;
	private List<KeyValue<String,Integer>> resultat=null;
	
	private ResultatFinal() {
	}
	/*utilitzem el patró Singleton per aquesta classe. És a dir, que només es pot crear una instància d'aquesta classe*/
	public static synchronized ResultatFinal getInstancia() {
		if(resultatFinal==null) {
			resultatFinal = new ResultatFinal();
		}
		return resultatFinal;
	}
	
	public synchronized void addParella(KeyValue<String,Integer> element) {
		if(resultat==null) {
			resultat=new ArrayList<>();
		}
		
		boolean trobat=false;
		Iterator<KeyValue<String,Integer>> iteratorActual = resultat.iterator();
		while((iteratorActual.hasNext()) && (!trobat)) {
			KeyValue<String,Integer> elementParella= iteratorActual.next();
			if(elementParella.getKey().equals(element.getKey())) {
				elementParella.setValue(element.getValue());
				trobat=true;
			}
		}
		if(trobat) {
			
		}
		else {
			resultat.add(element);

		}
		
	}
	
	public List<KeyValue<String,Integer>> getResult(){
		return 	resultat;
	}

	
	/*Funcio que s'encarrega de mostrar el resultat clau-valor*/
	public void mostrarResultatFinal() {
		for(KeyValue<String,Integer> result : resultat) {
			System.out.println("\t"+result.getKey()+" : "+result.getValue());
		}	
	}
	
	public void initResult() {
		resultat = null;
		resultatFinal = null;
	}
}
