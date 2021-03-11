import java.io.*;
import java.util.*;

public class main {

    private static List<Thread> llistaThreads=new ArrayList<Thread>();
    private static long tInici,tFinal,temps=0;

    public static void main(String[] args) throws IOException, InterruptedException  {

        String[] fitxer = {"entrada.txt","entrada1.txt"};
        /*Comprovem si es pasen per parametre fitxers,sino el llegim de la carpeta local*/
        if(args.length == 0) {
            System.out.println("NO HI HA FITXERS");
        }

        /*Si hi ha fitxers en els parametres, els transformem*/
        else {
            fitxer = args;
        }

        System.out. print("El programa s'executar� amb els seguents fitxers: ");
        int nFitxersIni=0;
        for(nFitxersIni = 0;nFitxersIni<(fitxer.length-1);nFitxersIni++) {
            System.out. print(fitxer[nFitxersIni]+", ");
        }
        System.out. println(fitxer[nFitxersIni]+". ");
        String sInput = "j";

        if(nFitxersIni>=1) {
            System.out. println("El resultat el vols amb els fitxers separats o junts (s/j)?");
            Scanner in = new Scanner(System.in);
            sInput = in. nextLine();
        }


        int nThreadsUtilitzats=0;


        ResultatFinal resultatfinal = ResultatFinal.getInstancia();

        for(int i = 0;i<(fitxer.length);i++) {
            tInici=System.currentTimeMillis();

            resultatfinal = ResultatFinal.getInstancia();
            BufferedReader bufferLlegirFitxer = new BufferedReader(new InputStreamReader(new FileInputStream(fitxer[i]), "utf-8"));

            String linia="";
            int nLineas = 0;
            String paragraf="";


            /*while per llegir linia a linia del fitxer rebut
             * cada iteracio s'executen les funcions Splitting, Mapping i els resultats es guarden a Shuffling*/
            while ((linia = bufferLlegirFitxer.readLine())!=null) {
                nLineas++;
                paragraf += linia+"\n";

                /*cada x linias...,
                 * diem que es un paragraf
                 * es crea un thread i li passem per parametre aquest paragraf*/
                if(nLineas == 60) {
                    Thread thread= new Thread(new Splitting(paragraf));
                    thread.start();
                    llistaThreads.add(thread);
                    nThreadsUtilitzats++;

                    /*Al final de cada iteracio buidem les variables per poder tornar a fer la iteracio*/
                    paragraf = "";
                    nLineas=0;
                }
            }
            /*Esperem a que tots els threads acabin la seva execucio*/
            for(int n = 0; n < llistaThreads.size(); n++) {
                llistaThreads.get(i).join();
            }

            /*Pot existir que les linies del fitxer no sigui multiple de 10,
             * les linies sobrants s'executen aqui*/
            if(nLineas != 0) {
                Thread thread= new Thread(new Splitting(paragraf));
                thread.start();
                thread.join();
            }

            /*Un cop acabem de llegir el fitxer, es tanca*/
            bufferLlegirFitxer.close();


            /*Un cop executat tots els threads amb totes les linies,
             * es recullen els resultats i s'executa el Reducing i es guarda a ResultatFinal*/
            List<List<KeyValue<String,Integer>>> llistaParelles = (Shuffling.getInstancia()).getList();
            llistaThreads = new ArrayList<Thread>();

            /*Per cada llista amb la mateixa clau...es crea un thread i s'executa el reducing amb aquella llista*/
            for(List<KeyValue<String,Integer>> element: llistaParelles) {
                Thread thread = new Thread(new Reducing(element));
                thread.start();
                llistaThreads.add(thread);
                nThreadsUtilitzats++;
            }
            for(int n = 0; n < llistaThreads.size(); n++) {
                llistaThreads.get(n).join();
            }
            tFinal=System.currentTimeMillis();
            temps+=tFinal-tInici;
            if(sInput.equals("s")) {
                System.out.println("\n"+fitxer[i]+":");

                /*Un cop executat totes les llistes, es recullen tots els resultats i els mostrem*/
                resultatfinal.mostrarResultatFinal();

                /*Execucio de la aplicacio grafica per mostrar els resultats amb el grafic*/
                new AplicacionGrafica(resultatfinal);

                /*Inicialitzem totes les classes singleton per tornarles a cridar*/
                resultatfinal.initResult();
                Shuffling.getInstancia().initSuffling();
            }


        }

        if(sInput.equals("j")) {

            for(int i = 0;i<(fitxer.length);i++) {
                System.out.print(fitxer[i]+" + ");
            }
            System.out.println("");

            /*Un cop executat totes les llistes, es recullen tots els resultats i els mostrem*/
            resultatfinal.mostrarResultatFinal();

            /*Execucio de la aplicacio grafica per mostrar els resultats amb le grafic*/
            new AplicacionGrafica(resultatfinal);
            tFinal=System.currentTimeMillis();

        }
        else {
            tFinal=System.currentTimeMillis();
        }
        //temps=tFinal-tInici; //per calcular el temps d'execuci�
        System.out.println("\nL'execuci� ha tardat: "+temps+" milisegons i s'han utilitzat: "+nThreadsUtilitzats+" threads.");
    }
}
