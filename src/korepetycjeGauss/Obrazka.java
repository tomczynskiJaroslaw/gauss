package korepetycjeGauss;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Obrazka {
	
	public static int[][] obrazekDoTablicy(String plik){
		BufferedImage obrazek = wczytaj(plik);
		int[][] tab = new int[obrazek.getHeight()][obrazek.getWidth()];
		for (int w=0;w<obrazek.getHeight();w++) {
			for (int s=0;s<obrazek.getWidth();s++) {
				//**)Mozeby pobrac dowolna z trzech skladowych RGB, bo w czarno bialym obrazie
				//wszystkie powinny miec taka sama wartosc
				tab[w][s] = new Color(obrazek.getRGB(s, w)).getBlue();
			}
		}
		return tab; 
	}

	private static BufferedImage wczytaj(String plik) {
		BufferedImage obrazek = null;
		try {
			obrazek = ImageIO.read(new File(plik));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obrazek;
	}
	
	public static void tablicaDoObrazka(int[][] tab,String plik) {
		int wYSOKOSC = tab.length;
		int sZEROKOSC = tab[0].length;
		BufferedImage obrazek = new BufferedImage(sZEROKOSC,wYSOKOSC,  BufferedImage.TYPE_INT_RGB);
		for (int w=0;w<wYSOKOSC;w++) {
			for (int s=0;s<sZEROKOSC;s++) {
				//**)
				int a = tab[w][s];
				obrazek.setRGB(s, w, new Color(a,a,a).getRGB() ) ;
			}
		}
		zapisz(obrazek,plik);
	}

	private static void zapisz(BufferedImage obrazek,String plik) {
		try {
			ImageIO.write(obrazek, "png", new File(plik));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
