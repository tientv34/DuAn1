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
public class ModelImage {

    private Long id;
    private String maAnh;
    private String tenAnh;
    private String duongDanAnh;

    public ModelImage() {
    }

    public ModelImage(Long id, String maAnh, String tenAnh, String duongDanAnh) {
        this.id = id;
        this.maAnh = maAnh;
        this.tenAnh = tenAnh;
        this.duongDanAnh = duongDanAnh;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaAnh() {
        return maAnh;
    }

    public void setMaAnh(String maAnh) {
        this.maAnh = maAnh;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    

  
    
}
