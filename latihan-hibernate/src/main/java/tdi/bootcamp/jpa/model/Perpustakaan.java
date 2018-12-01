package tdi.bootcamp.jpa.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "data_perpustakaan", schema = "public")
public class Perpustakaan {

    @Id @GeneratedValue
    private Integer id;

    @Column(name = "nama_perpusakaan", length = 50)
    private String namaPerpustakaan;

    @OneToMany(mappedBy = "perpustakaan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Map<String, Book> daftarBuku;








    public Map<String, Book> getDaftarBuku() {
        return daftarBuku;
    }
    public void setDaftarBuku(Map<String, Book> daftarBuku) {
        this.daftarBuku = daftarBuku;
    }
    public String getNamaPerpustakaan() {
        return namaPerpustakaan;
    }
    public void setNamaPerpustakaan(String namaPerpustakaan) {
        this.namaPerpustakaan = namaPerpustakaan;
    }

}
