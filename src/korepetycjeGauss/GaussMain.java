package korepetycjeGauss;

import java.util.ArrayList;
import java.util.List;

public class GaussMain {
	private static int liczbaWatkow = 2;
	private static String plikTablica = "tablica.png";
	private static String plikWynik = "wynik.png";
	public static void main(String[] args) {
		int[][] tablica = Obrazka.obrazekDoTablicy(plikTablica);
		wypisz(tablica);
		
		Watek.inicjalizacja(tablica);
		rozdzielNaWatki(tablica,liczbaWatkow);
		
		wypisz(Watek.getWynik());
		Obrazka.tablicaDoObrazka(Watek.getWynik(),plikWynik);
	}

	private static void rozdzielNaWatki(int[][] tab,int ileWatkow) {
		List<Thread> listaWatkow = new ArrayList<Thread>();
		
		int wYSOKOSC = tab.length;
		int sZEROKOSC = tab[0].length;
		int czescSzer = (sZEROKOSC-2)/ileWatkow;
		int czescWys = (wYSOKOSC-2)/ileWatkow;
		for (int s=0;s<ileWatkow;s++) {
			for (int w=0;w<ileWatkow;w++) {
				stworzWatek(listaWatkow, 1+(s*czescSzer), 1+(w*czescWys), ((s+1)*czescSzer), ((w+1)*czescWys));//zielone
			}
		}
		stworzWatek(listaWatkow, ileWatkow*czescSzer, 1, sZEROKOSC-2, ileWatkow*czescWys);//ciemno niebieskie
		stworzWatek(listaWatkow, ileWatkow*czescSzer, ileWatkow*czescWys, sZEROKOSC-2, wYSOKOSC-2);//blekitne
		stworzWatek(listaWatkow,  1, ileWatkow*czescWys, ileWatkow*czescSzer, wYSOKOSC-2);//zolte
		
		for (Thread t: listaWatkow)
			czekajNaWatek(t);
		
	}

	private static void czekajNaWatek(Thread t) {
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void stworzWatek(List<Thread> listaWatkow,int s, int w, int szerokosc, int wysokosc) {
		Watek watek = new Watek(s,w,szerokosc,wysokosc);
		watek.start();
		listaWatkow.add(watek);
	}

	public static void wypisz(int[][] tab) {
		System.out.println("---");
		int wYSOKOSC = tab.length;
		int sZEROKOSC = tab[0].length;
		for (int i = 0; i < wYSOKOSC; i++) {
			for (int j = 0; j < sZEROKOSC; j++) {
				System.out.print(tab[i][j]+"\t");
			}
			System.out.println();
		}
	}


}
