package tdi.bootcamp.jpa.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "data_peminjam", schema = "public")
public class Peminjam {

    @Id @GeneratedValue
    private Integer id;

    @Column(name = "nama", length = 30)
    private String nama;

    @Column(name = "alamat", length = 50)
    private String alamat;

    @OneToMany(mappedBy = "yangPeminjam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Map<String, Book> daftarBuku;

    @OneToOne
    @JoinColumn(name = "id_buku")
    private Book buku;























    public void setBuku(Book buku) {
        this.buku = buku;
    }

    public Book getBuku() {
        return buku;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setDaftarBuku(Map<String, Book> daftarBuku) {
        this.daftarBuku = daftarBuku;
    }

    public Map<String, Book> getDaftarBuku() {
        return daftarBuku;
    }
}
