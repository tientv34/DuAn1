/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author Admin
 */
public class ModelDonViTinh {
    private Long id;
    private String tenDVT;
    private ModelImage imageID;

    public ModelDonViTinh(Long id, String tenDVT, ModelImage imageID) {
        this.id = id;
        this.tenDVT = tenDVT;
        this.imageID = imageID;
    }

    public ModelDonViTinh() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDVT() {
        return tenDVT;
    }

    public void setTenDVT(String tenDVT) {
        this.tenDVT = tenDVT;
    }

    public ModelImage getImageID() {
        return imageID;
    }

    public void setImageID(ModelImage imageID) {
        this.imageID = imageID;
    }
    
    
}
