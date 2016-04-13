import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Karunia Yusuf
 * @version 2016.04.13
 * 
 * Program ini adalah Game Acak Kata
 * Soal yang diberikan adalah suatu kata yang urutan hurufnya telah diacak
 * Jawaban yang diminta adalah kata perbaikan dari soal tersebut
 * Setiap pemain mendapatkan 3x kesempatan salah, lebih dari itu permainan akan berakhir
 * Jika soal dijawab dengan benar, poin akan bertambah
 */

public class AcakKata {

	public static void main(String[] args) throws IOException {
		
		// Inisialisasi variabel
		BufferedReader rd = new BufferedReader(new FileReader("daftarKata.txt"));
		Scanner sc = new Scanner(System.in);
		ArrayList<String> daftar = new ArrayList<String>();
		Random ran = new Random();
		String input = "";
		int kesempatan = 3;
		int poin = 0;
		
		// Memasukkan data pada daftar kata ke dalam suatu arraylist
		while ((input = rd.readLine()) != null && input.length() > 0)
			daftar.add(input);
		
		// Cetak pesan pembuka permainan
		System.out.println("Selamat datang di Game Acak Kata!");
		System.out.println("Jadikan kata yang acak menjadi benar");
		System.out.println("Jika salah 3x, Game Over :(");
		System.out.print("Sudah siap? (y/n) ");
		
		// Penentuan kelanjutan permainan
		String lanjut = sc.nextLine().trim().toLowerCase();
		if (lanjut.equals("n"))
			kesempatan = 0;
		
		// Permainan berlangsung selama masih memiliki kesempatan
		while (kesempatan > 0) {
			
			// Menentukan kata yang akan digunakan dari daftar kata
			int idxSoal = ran.nextInt(daftar.size());
			String jawaban = daftar.get(idxSoal);
			
			// Membuat arraylist penyimpanan urutan index acak untuk soal
			ArrayList<Integer> tempIdxSoal = new ArrayList<Integer>();
			
			// Memasukkan satu nilai index ke dalam tempIdxSoal
			int idx = ran.nextInt(jawaban.length());
			tempIdxSoal.add(idx);
			
			// Memasukkan seluruh index acak untuk soal ke dalam tempIdxSoal
			int a = 1; // Index pada tempIdxSoal
			while (a < jawaban.length()) {
				if (!tempIdxSoal.contains(idx)) {
					tempIdxSoal.add(idx);
					a++;
				}
				else
					idx = ran.nextInt(jawaban.length());
			}
			
			// Membuat soal sesuai dengan urutan index acak
			String soal = "";
			for (int i = 0; i < jawaban.length(); i++) {
				int temp = tempIdxSoal.get(i);
				soal += jawaban.charAt(temp);
			}
			
			// Cetak pertanyaan
			System.out.println("Hayo... ini kata apa yaa? " + soal);
			System.out.print("Jawab: ");
			String jawab = sc.nextLine().trim().toLowerCase();
			
			// Validasi jawaban
			if (jawab.equals(jawaban)) {
				System.out.println("Yeay! Kamu benar! :D");
				poin += 10;
				System.out.println("Poin kamu sekarang adalah " + poin);
			}
			else {
				kesempatan--;
				System.out.println("Huhu kamu salah :( jawabannya adalah " + jawaban);
				if (kesempatan > 0)
					System.out.println("Kamu masih punya " + kesempatan + " kesempatan kok, semangat!");
				else
					System.out.println("Sayang sekali, kesempatanmu habis...");
			}
		}
		
		// Cetak pesan akhir permainan
		System.out.println("Game Over!");
		System.out.println("Terima kasih telah memainkan game ini yaa! :)");
		
		rd.close();
		sc.close();

	}

}
