import java.util.List;

/*Classe que s'encarrega de sumar els valors de totes les claus de la llista*/
public class Reducing implements Runnable{
	
	private List<KeyValue<String,Integer>> llistaParelles;
	
	public Reducing(List<KeyValue<String,Integer>> dades) {
		llistaParelles = dades;
	}

	
	public void sumValors() {
		int  suma = 0;
		
		/*Sumem tots els valors de la llista, les claus de la llista son les mateixes*/
		for(KeyValue<String,Integer> clauValor : llistaParelles) {
			suma+=(int)clauValor.getValue();
		}
		
		/*Agafem la instancia del ResultatFinal
		 * i li passem per parametre el resultat de la suma amb la clau corresponent*/
		ResultatFinal resultat = ResultatFinal.getInstancia();
		KeyValue<String,Integer> element = new KeyValue<>(llistaParelles.iterator().next().getKey(),suma);
		resultat.addParella(element);
	}
	@Override
	public void run() {
		/*S'executa quan el thread start*/
		sumValors();
	}
}
