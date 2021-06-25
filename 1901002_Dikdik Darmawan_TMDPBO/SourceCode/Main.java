/* Saya Dikdik Darmawan mengerjakan Tugas Masa Depan dalam mata kuliah Desain
 Pemrograman Berorientasi Objek untuk keberkahanya maka saya tidak melakukan kecurangan 
 seperti yang telah di spesifikasikan. Aamiin. */

import View.GUI.*;

public class Main{
	public static Home home;
	public static void main(String[] args) {
		try{
			home =  (Home) new Home();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
} 