/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Entity.KhuVuc;

/**
 *
 * @author Admin
 */
public class ModelBan {
    private Long id;
    private String maBan;
    private String tenBan;
    private ModelKhuVuc khuVucID;
    private int trangThai;

    public ModelBan() {
    }

    public ModelBan(Long id, String maBan, String tenBan, ModelKhuVuc khuVucID, int trangThai) {
        this.id = id;
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.khuVucID = khuVucID;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public ModelKhuVuc getKhuVucID() {
        return khuVucID;
    }

    public void setKhuVucID(ModelKhuVuc khuVucID) {
        this.khuVucID = khuVucID;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    
    
}
