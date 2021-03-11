import java.util.Arrays;

/*Classe que s'encarrega de netejar els caracters especials, per tenir nomes les paraules
 * En la creacio de la classe es neteja el paragraf
 * En el start del thread s'executa el run()
 * */
public class Splitting implements Runnable{
	private String clearP="";

	public Splitting(String text) {
		this.clearP = text;
		clearParagraph(clearP);
	}

	public void clearParagraph(String text) {

		clearP = clearP.replace(",","");
		clearP = clearP.replace(".","");
		clearP = clearP.replace(":","");
		clearP = clearP.replace(";","");
		clearP = clearP.replace("\n"," ");
		clearP = clearP.toLowerCase();
	}

	@Override
	public void run() {
		/*quan cridem a la classe Mapping li passem el par�graf net, lliure de car�cters especials*/
		new Mapping(clearP);
	}
}
