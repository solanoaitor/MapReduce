import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * Classe que ens servira per visualitzar el grafic, en el nostre cas, un grafic de barres
 */
public class AplicacionGrafica extends JFrame{
 
    /*Finestra que utilitzarem per mostra el grafic*/
    private JPanel contentPane;
    
    /*Constructor de la classe*/
    public AplicacionGrafica(ResultatFinal result){
    	runAplicacion(result);
    	
    }
    
    public void runAplicacion(ResultatFinal result) {
List<KeyValue<String,Integer>> resultatFinal=result.getResult();
    	
    	/*Editem el resultat final per tenir nomes una llista amb els valors que volem en el grafic*/
    	List<KeyValue<String,Integer>> resultatFinalEditat = new ArrayList<>();
    	
    	int nParaules=0;
    	int maxRepeticions=0;
        for(KeyValue<String,Integer> parellaClauValor : resultatFinal) {
        	if(parellaClauValor.getValue()>0) {
        		if(parellaClauValor.getValue()>maxRepeticions) {
        			maxRepeticions=parellaClauValor.getValue();
        		}
        		resultatFinalEditat.add(parellaClauValor);
        		nParaules+=1;
        	}
        }
        
        /*Construim una llista de 0...maxRepeticions per mostrar-la al grafic*/
    	List<Integer> listNumsEixHoritzontal = new ArrayList<>();
        for(int i=0;i<=maxRepeticions;i++) {
        	listNumsEixHoritzontal.add(i);
        }
       
        /*Donem titul a la finestra del grafic*/
        setTitle("Gràfic Repeticions");
 
        /*Donem tamany a la finestra (posicioX,posicioY,longX,longY)*/
        setBounds(400, 200, (maxRepeticions*70)+30, ((nParaules+5)*20)+50);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
         contentPane =new JPanel();
 
        contentPane.setLayout(null);
 
 

        /*-----------------------Creacio dels components del grafic-----------------------*/
        JLabel elementGrafic;
        
        
        /*Nom eix horitzontal*/
        elementGrafic=new JLabel("nRepeticions");
        elementGrafic.setBounds(220, 15, 100, 20);
        contentPane.add(elementGrafic);
        
        /*Valos possibles eix horitzontal*/
        for(int eix=0;eix<=maxRepeticions;eix++) {
        	elementGrafic=new JLabel(listNumsEixHoritzontal.get(eix).toString());
        	elementGrafic.setBounds(150+(32*eix), 40, 100, 20);
            contentPane.add(elementGrafic);
        }
        
        
        /*Nom eix vertical*/
        elementGrafic=new JLabel("clau");
        elementGrafic.setBounds(60, 20, 100, 20);
        contentPane.add(elementGrafic);
        
       /*Valors possibles eix vertical*/        
        int posVertical =1;
        for(KeyValue<String,Integer> clauValor : resultatFinalEditat) {
            elementGrafic=new JLabel(clauValor.getKey());
            elementGrafic.setBounds(60, (posVertical*20)+50, 100, 20);
            contentPane.add(elementGrafic);
            
            for(int columna=0;columna<(clauValor.getValue()*8);columna++) {
            	elementGrafic=new JLabel("█");
            	elementGrafic.setBounds(150+(columna*4), (posVertical*20)+50, 100, 20);
	            contentPane.add(elementGrafic);
	            columna+=1;
			}
            
            posVertical+=1;
        }
        contentPane.setPreferredSize(new Dimension((maxRepeticions*70)+25,((nParaules+5)*20)+45));
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(230,10,500,500);
        jScrollPane.setViewportView(contentPane);

        add(jScrollPane);
        
        setVisible(true);

        resultatFinalEditat=null;
    }
}

