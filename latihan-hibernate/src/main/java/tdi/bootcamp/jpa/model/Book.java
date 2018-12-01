package tdi.bootcamp.jpa.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "data_buku", schema = "public")
public class Book {

    @Id @GeneratedValue
    private Integer id;

    @Column(name = "judul", length = 30)
    private String title;

    @Column(name = "penulis", length = 30)
    private String author;

    @Column(name = "statusPinjam")
    private boolean isPinjam;

    @ManyToOne
    @JoinColumn(name = "id_perpus")
    private Perpustakaan perpustakaan;

    @ManyToOne
    @JoinColumn(name = "id_peminjam")
    private  Peminjam  yangPeminjam;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getIsPinjam() {
        return isPinjam;
    }

    public void setPinjam(boolean isPinjam) {
        this.isPinjam = isPinjam;
    }

    public Perpustakaan getPerpustakaan() {
        return perpustakaan;
    }

    public void setPerpustakaan(Perpustakaan perpustakaan) {
        this.perpustakaan = perpustakaan;
    }

    public void setYangPeminjam(Peminjam yangPeminjam) {
        this.yangPeminjam = yangPeminjam;
    }

    public Peminjam getYangPeminjam() {
        return yangPeminjam;
    }
}
