package korepetycjeGauss;

public class Watek extends Thread{
	private static int[][] tablica;
	private static int[][] wynik;
	private int s;
	private int w;
	private int szerokosc;
	private int wysokosc;
	
	
	public Watek( int s, int w, int szerokosc, int wysokosc) {
		super();
		this.s = s;
		this.w = w;
		this.szerokosc = szerokosc;
		this.wysokosc = wysokosc;
	}
	
	public static void inicjalizacja(int[][] tablica) {
		Watek.tablica=tablica;
		
		int wYSOKOSC  = tablica.length;
		int sZEROKOSC = tablica[0].length;
		wynik = new int[wYSOKOSC][sZEROKOSC];
		wynik = uzupelnijKrawedzie(tablica);
	}
	
	public static int[][] getWynik() {
		return wynik;
	}

	@Override
	public void run() {
		rozmycieGaussa(tablica, s, w, szerokosc, wysokosc);
	}
	
	private static void rozmycieGaussa(int[][] tablica,int s,int w,int szerokosc,int wysokosc) {
			for (int i = w; i <= wysokosc; i++) {
				for (int j = s; j <= szerokosc; j++) {
					int suma = 0;
					suma += tablica[i + 1][j];
					suma += tablica[i + 1][j + 1];
					suma += tablica[i][j + 1];
					suma += tablica[i - 1][j + 1];
					suma += tablica[i - 1][j];
					suma += tablica[i - 1][j - 1];
					suma += tablica[i][j - 1];
					suma += tablica[i + 1][j - 1];
					wynik[i][j] = suma / 8;
				}
			}
	}

	private static int[][] uzupelnijKrawedzie(int[][] tablica) {
		int wysokosc = tablica.length;
		int szerokosc = tablica[0].length;
		for (int w = 0; w < wysokosc; w++) {
			wynik[w][0] = tablica[w][0];
			wynik[w][szerokosc - 1] = tablica[w][szerokosc - 1];
		}
		for (int s = 0; s < szerokosc; s++) {
			wynik[0][s] = tablica[0][s];
			wynik[wysokosc - 1][s] = tablica[wysokosc - 1][s];
		}
		return wynik;
	}
}
