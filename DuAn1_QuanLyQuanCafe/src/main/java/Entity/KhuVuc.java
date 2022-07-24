/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KhuVuc")
public class KhuVuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAKHUVUC")
    private String maKhuVuc;

    @Column(name = "TENKHUVUC", columnDefinition = "NVARCHAR(255)")
    private String tenKhuVuc;

    @ManyToOne
    @JoinColumn(name = "IMAGEID")
    private Image imageID;
    
    @OneToMany(mappedBy="khuVuc", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ban> Ban;
    
    @Column(name = "TABHIENTHI")
    private int tab;

    public KhuVuc() {
    }

    public KhuVuc(Long id, String maKhuVuc, String tenKhuVuc, Image imageID, int tab) {
        this.id = id;
        this.maKhuVuc = maKhuVuc;
        this.tenKhuVuc = tenKhuVuc;
        this.imageID = imageID;
        this.tab = tab;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public Image getImageID() {
        return imageID;
    }

    public void setImageID(Image imageID) {
        this.imageID = imageID;
    }

    public List<Ban> getBan() {
        return Ban;
    }

    public void setBan(List<Ban> Ban) {
        this.Ban = Ban;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    
   
}
