/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Entity.Image;
/**
 *
 * @author Admin
 */
public class ModelKhuVuc {
    private Long id;
    private String maKhuVuc;
    private String tenKhuVuc;
    private ModelImage imageID;
    private int tab;

    public ModelKhuVuc() {
    }

    public ModelKhuVuc(Long id, String maKhuVuc, String tenKhuVuc, ModelImage imageID, int tab) {
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

    public ModelImage getImageID() {
        return imageID;
    }

    public void setImageID(ModelImage imageID) {
        this.imageID = imageID;
    }
    
    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
    
    
}
