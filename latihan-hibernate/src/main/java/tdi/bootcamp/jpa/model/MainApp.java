package tdi.bootcamp.jpa.model;

import org.hibernate.Session;
import org.hibernate.query.Query;
import tdi.bootcamp.jpa.model.util.HibernateUtil;

import java.util.*;


public class MainApp {
	
	private static String getNativeQuery(Session session, String sql)  {
		return (String) session.createNativeQuery(sql).getSingleResult();
	}

	//INSERT : Data pada pada tabel perpustakaan dan tabel buku
	private static Integer simpanPerpus (Session session){
		Perpustakaan perpustakaan = new Perpustakaan();
		perpustakaan.setNamaPerpustakaan("BAPUSIPDA");

		Map<String, Book> daftarBuku = new HashMap<>();
		for (int i = 1; i < 4; i++){
			Book buku = new Book();
			buku.setTitle("Arduino Edisi ke- " + i);
			buku.setAuthor("Camble");
			buku.setPinjam(false);
			buku.setPerpustakaan(perpustakaan);
			daftarBuku.put("BUKU " + i, buku);
		}

		perpustakaan.setDaftarBuku(daftarBuku);

		return (Integer) session.save(perpustakaan);
	}

	//INSERT : Data pada tabel peminjam
	private static Integer simpanPeminjam(Session session) {
		Peminjam peminjam = new Peminjam();
		peminjam.setNama("Roro");
		peminjam.setAlamat("Klaten");

		return (Integer) session.save(peminjam);
	}

	//SET: Data buku yang dipinjam
	private static void setPeminjam(Session session) {
		Query setInBuku = session.createQuery("UPDATE Book SET yangPeminjam = 136, isPinjam = true"
				+ " WHERE id = 132");

		Query setInPeminjam = session.createQuery("UPDATE Peminjam SET buku = 132"
				+ " WHERE id = 136");

		int rsInBuku = setInBuku.executeUpdate();
		int rsInPeminjam = setInPeminjam.executeUpdate();
	}


	//DELETE : Data pada tabel buku
	private static void deleteBuku(Session session){
		Book buku = session.find(Book.class, 32);
		session.delete(buku);
	}

	//DELETE : Data pada tabel perpustakaan
	private static void deletePerpustakaan(Session session){
		Perpustakaan perpustakaan = session.find(Perpustakaan.class, 31);
		session.delete(perpustakaan);
	}


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	//FUNGSI : Mencari nama buku yg dipinjam berdasar nama peminjam
	private static Set<Book> getBuku(Session session){
		List<Book> listData = session.createQuery("SELECT k.buku FROM Peminjam k WHERE k.nama = :namaPeminjam")
				.setParameter("namaPeminjam", "Roro")
				.getResultList();

		return new HashSet<>(listData);
	}

	//FUNGSI : Mencari nama peminjam berdasar buku
	private static Peminjam getNamaPeminjam(Session session) {
		Peminjam listData = (Peminjam) session.createQuery("SELECT k.yangPeminjam FROM Book k WHERE k.title = :judulBuku")
				.setParameter("judulBuku", "Biologi jilid ke- 2")
				.getResultList().get(0);

		return listData;
	}

	//FUNGSI : Mencari daftar buku berdasar nama perpustakaan
	private static Set<Book> getDaftarBukuPerpus(Session session) {
		List<Book> listData = session.createQuery("SELECT k.daftarBuku FROM Perpustakaan k WHERE k.namaPerpustakaan = :namaPerpus")
				.setParameter("namaPerpus", "BAPUSIPDA")
				.getResultList();

		return new HashSet<>(listData);
	}

	//FUNGSI : Melihat status buku (dipinjam atau tidak)
	private static Book getStatusPinjamanBuku(Session session) {
		Book listData = (Book) session.createQuery("FROM Book WHERE title = :judulBuku")
				.setParameter("judulBuku", "Biologi jilid ke- 1")
				.getResultList().get(0);

		return listData;
	}


	public static void main (String[] args) {
		System.out.println("Hibernate one to one (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		//-------------------------- MASUKKAN SINTAKS UNTUK MEMPROSES DATA DISINI --------------------------------------//

		//OUTPUT : getBuku
		/*
		Set<Book> bookSet = MainApp.getBuku(session);
		for(Iterator iterator = bookSet.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			System.out.println("nama buku ====>" + book.getTitle());
		}*/

		//OUTPUT : getNamaPeminjam
		/*
		Peminjam peminjam =	MainApp.getNamaPeminjam(session);
		System.out.println("nama peminjam <><><> : " + peminjam.getNama());
		*/


		//OUTPUT : getDaftarBukuPerpus
		/*
		Set<Book> bookSet = MainApp.getDaftarBukuPerpus(session);
		for(Iterator iterator = bookSet.iterator(); iterator.hasNext();) {
			Book daftarBuku = (Book) iterator.next();
			System.out.println("Daftar buku ====> " +  daftarBuku.getId() + " :  "+ daftarBuku.getTitle());
		}*/

		//OUTPUT : getStatusPinjamanBuku
		Book stsBuku = MainApp.getStatusPinjamanBuku(session);
		System.out.println("Buku dengan judul : " + stsBuku.getTitle() + " ; Status Pinjaman ===> " + stsBuku.getIsPinjam());

		//EKSEKUSI :Fungsi rollback jika ada sintaks yang error
		session.flush();
		session.close();

		HibernateUtil.shutdown();
	}
	
}
