/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ViewsDuAn;

import Services.BanService;
import Services.NhomSanPhamJList;
import Services.IService;
import Services.KhachHangService;
import Services.KhuVucJList1;
import Services.KhuVucService;
import Services.NhomSanPhamService;
import Services.SanPhamService;
import Utilities.XDate;
import ViewModels.ModelBan;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhuVuc;
import ViewModels.ModelNhomSanPham;
import ViewModels.ModelSanPham;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Main extends javax.swing.JFrame {

    DefaultTableModel _DefaultTableModel;
    IService<ModelKhuVuc> _KhuVucService;
    IService<ModelBan> _BanService;
    IService<ModelSanPham> _SanPhamService;
    IService<ModelNhomSanPham> _NhomSanPhamService;
    ChiTietKhuVuc _ChiTietKhuVuc;
    ChiTietBan _ChiTietBan;
    ChiTietNhomSanPham _ChiTietNhomSanPham;
    ChitietSanPham _ChitietSanPham;
    ChiTietKhachHang _ChiTietKhachHang;
    String _duongDanAnh = "";
    JLabel _labelAnh;
    JLabel _labelTenBan;
    IService<ModelKhachHang> _khachHangIService = new KhachHangService();
    DefaultTableModel _deDefaultTableModel = new DefaultTableModel();
    int _currentPage;
     int _pageSize;

    public Main() {
        initComponents();
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        setSize(r.width, r.height);
        _currentPage = 1;
        _pageSize = 20;
        panel_khuvuc.setPreferredSize(new Dimension(r.width / 2, r.height - 20));
        panel_khuvuc.setLayout(new GridLayout(0, 1));
        this.setTitle("PHẦN MỀM QUẢN LÝ CAFE - HOTLINE 0392 133 732 ");
        setLogo();
        jToolBar1.setFloatable(false);
        _SanPhamService = new SanPhamService();
        _NhomSanPhamService = new NhomSanPhamService();
        _KhuVucService = new KhuVucService();
        _BanService = new BanService();
        listNhomSanPhamInJList(list_douong, this);
        listNhomSanPhamInJList(list_NhomSP, this);
        listKhuVucInJList(list_KhuVuc, this);
        loadTableSanPham(_SanPhamService.selectAll(0, 20));
        loadTabKhuVuc();
        btn_moban.setEnabled(false);
        loadDateKhachHang(_khachHangIService.selectAll(0, 20));
    }
    
    //load data Khach Hang;
    void loadDateKhachHang(List<ModelKhachHang> listKhachHang){
        _deDefaultTableModel = (DefaultTableModel) tbl_KhachHang.getModel();
        _deDefaultTableModel.setRowCount(0);
        int stt = 1;
        for (ModelKhachHang x : listKhachHang) {
            _deDefaultTableModel.addRow(new Object[]{
                stt++,x.getMaKhachHang(), x.getTenKhachHang(),x.getDiaChi(),x.getDienThoai(),x.getEmail(),x.getFax(),x.getGhiChu()
            });
        }
    }
    
    //Lấy 1 đối tượng từ table Khách Hàng
     ModelKhachHang GUIKH(){
        int index = tbl_KhachHang.getSelectedRow();
        return this._khachHangIService.getListFromDB().get(index);
    }

    void listNhomSanPhamInJList(JList list, JFrame frame) {
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        listModel.addElement(new ModelNhomSanPham(Long.MIN_VALUE, null, "Tất cả", null, WIDTH));
        for (ModelNhomSanPham x : _NhomSanPhamService.selectAll(0, 1000)) {
            listModel.addElement(new ModelNhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), x.getImageID(), x.getTrangthai()));
        }
        list.setCellRenderer(new NhomSanPhamJList());
        list.setModel(listModel);
    }

    void listKhuVucInJList(JList list, JFrame frame) {
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        listModel.addElement(new ModelKhuVuc(Long.MIN_VALUE, null, "Tất cả", null, WIDTH));
        for (ModelKhuVuc x : _KhuVucService.selectAll(0, 1000)) {
            listModel.addElement(new ModelKhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), x.getImageID(), x.getTab()));
        }

        list.setCellRenderer(new KhuVucJList1());
        list.setModel(listModel);
    }

    void loadTabKhuVuc() {
        try {
            JPanel[] panelTab = new JPanel[_KhuVucService.selectAll(0, 10).size()];
            JPanel[] panelBan = new JPanel[_BanService.selectAll(0, 10).size()];
            JLabel[] labelTenBan = new JLabel[_BanService.getListFromDB().size()];
            JLabel[] labelAnhBan = new JLabel[_BanService.getListFromDB().size()];
            for (int i = 0; i < _KhuVucService.getListFromDB().size(); i++) {
                panelTab[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                panelTab[i].setBackground(Color.WHITE);
                ImageIcon icon = new ImageIcon("src\\main\\resources\\ImageLogin\\" + _KhuVucService.getListFromDB().get(i).getImageID().getDuongDanAnh());
                Image image = icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon1 = new ImageIcon(image);
                if (_KhuVucService.getListFromDB().get(i).getTab() == 1) {
                    tab_khuvucduoi.addTab(_KhuVucService.getListFromDB().get(i).getTenKhuVuc(), icon1, panelTab[i]);
                } else {
                    tab_khuvuctren.addTab(_KhuVucService.getListFromDB().get(i).getTenKhuVuc(), icon1, panelTab[i]);
                }
                for (int j = 0; j < _BanService.getListFromDB().size(); j++) {
                    if (_BanService.getListFromDB().get(j).getKhuVucID().getId() == _KhuVucService.getListFromDB().get(i).getId()) {
                        panelBan[j] = new JPanel();
                        panelBan[j].addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                JPanel panel = (JPanel) evt.getSource();
                                JLabel label = (JLabel) panel.getComponent(1);
                                JLabel label1 = (JLabel) panel.getComponent(0);
                                getLabelAnh(label1);
                                getLabelTenBan(label);
                                String duongDanAnh = checkBan(label.getText());
                                getDuongDanAnh(duongDanAnh);
                            }
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                JPanel panel = (JPanel) evt.getSource();
                                panel.setBackground(new Color(85,172,238));
                            }
                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                JPanel panel = (JPanel) evt.getSource();
                                panel.setBackground(Color.WHITE);
                            }

                        });
                        panelBan[j].setBackground(Color.WHITE);
                        BoxLayout boxlayout = new BoxLayout(panelBan[j], BoxLayout.Y_AXIS);
                        panelBan[j].setLayout(boxlayout);
                        panelBan[j].setPreferredSize(new Dimension(100, 100));
                        labelTenBan[j] = new JLabel(_BanService.getListFromDB().get(j).getTenBan());
                        labelTenBan[j].setAlignmentX(CENTER_ALIGNMENT);
                        labelAnhBan[j] = new JLabel();
                        labelAnhBan[j].setAlignmentX(CENTER_ALIGNMENT);
                        if (_BanService.getListFromDB().get(j).getTrangThai() == 0) {
                            labelAnhBan[j].setIcon(new ImageIcon("src\\main\\resources\\ImageLogin\\1" + _KhuVucService.getListFromDB().get(i).getImageID().getDuongDanAnh()));
                        } else {
                            labelAnhBan[j].setIcon(new ImageIcon("src\\main\\resources\\ImageLogin\\" + _KhuVucService.getListFromDB().get(i).getImageID().getDuongDanAnh()));
                        }
                        panelBan[j].add(labelAnhBan[j]);
                        panelBan[j].add(labelTenBan[j]);
                        panelBan[j].add(Box.createRigidArea(new Dimension(0, 10)));
                        panelTab[i].add(panelBan[j]);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    void getLabelAnh(JLabel label) {
        _labelAnh = label;
    }

    void getLabelTenBan(JLabel label) {
        _labelTenBan = label;
    }

    void getDuongDanAnh(String txt) {
        _duongDanAnh = txt;
    }

    void setNgayGio() {
        cbc_gio.setEnabled(false);
        cbc_gio.addItem(XDate.getGio());
        cbc_ngay.setEnabled(false);
        cbc_ngay.addItem(XDate.now().toString());
    }

    String checkBan(String tenBan) {
        for (ModelBan x : _BanService.getListFromDB()) {
            if (x.getTenBan().equals(tenBan) && x.getTrangThai() == 0) {
                btn_moban.setEnabled(true);
                return x.getKhuVucID().getImageID().getDuongDanAnh();
            }
        }
        btn_moban.setEnabled(false);
        return null;
    }

    void loadTableSanPham(List<ModelSanPham> list) {
        _DefaultTableModel = (DefaultTableModel) tbl_mathang.getModel();
        _DefaultTableModel.setRowCount(0);
        for (ModelSanPham x : list) {
            _DefaultTableModel.addRow(new Object[]{
                x.getTenSanPham(), x.getDVT().getTenDVT(), x.getDonGia()
            });
        }
        Color ivory = new Color(255, 255, 255);
        tbl_mathang.setOpaque(true);
        tbl_mathang.setFillsViewportHeight(true);
        tbl_mathang.setBackground(ivory);

    }

    void setLogo() {
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\Image\\logo.png");
            this.setIconImage(icon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi logo");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        tab_chinh = new javax.swing.JTabbedPane();
        sudungdichvu = new javax.swing.JPanel();
        panel_mathang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_douong = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_mathang = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        panel_hoadon = new javax.swing.JPanel();
        cbc_gio = new javax.swing.JComboBox<>();
        btn_moban = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbc_ngay = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_mathang1 = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        panel_button = new javax.swing.JPanel();
        btn_chuyenban = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        btn_them = new javax.swing.JButton();
        btn_giam = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        panel_khuvuc = new javax.swing.JPanel();
        tab_khuvuctren = new javax.swing.JTabbedPane();
        tab_khuvucduoi = new javax.swing.JTabbedPane();
        danhmucbankhuvuc = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton28 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        list_KhuVuc = new javax.swing.JList<>();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jToolBar5 = new javax.swing.JToolBar();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton29 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        danhmucnhomsanpham = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jToolBar6 = new javax.swing.JToolBar();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton33 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jToolBar4 = new javax.swing.JToolBar();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton36 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        list_NhomSP = new javax.swing.JList<>();
        danhmuckhachang = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jToolBar8 = new javax.swing.JToolBar();
        jButton40 = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_XoaKH = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btn_loadData = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txt_timkiem = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_KhachHang = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sudungdichvu.setBackground(new java.awt.Color(187, 206, 230));

        panel_mathang.setBackground(new java.awt.Color(187, 206, 230));

        list_douong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_douongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list_douong);

        tbl_mathang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mặt hàng", "ĐVT", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_mathang);

        jLabel10.setText("Tìm(F3):");

        javax.swing.GroupLayout panel_mathangLayout = new javax.swing.GroupLayout(panel_mathang);
        panel_mathang.setLayout(panel_mathangLayout);
        panel_mathangLayout.setHorizontalGroup(
            panel_mathangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_mathangLayout.createSequentialGroup()
                .addGroup(panel_mathangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_mathangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_mathangLayout.setVerticalGroup(
            panel_mathangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mathangLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panel_mathangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel_hoadon.setBackground(new java.awt.Color(187, 206, 230));

        btn_moban.setText("Mở bàn");
        btn_moban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mobanActionPerformed(evt);
            }
        });

        jLabel1.setText("Ngày:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Bàn A1");

        jLabel3.setText("Số TT:");

        jTextField1.setText("1");

        jToolBar2.setRollover(true);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton13);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/tru.png"))); // NOI18N
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton14);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/calculator.png"))); // NOI18N
        jButton15.setText("Đặt số lượng");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton15.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton15);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ghichu.png"))); // NOI18N
        jButton17.setText("Ghi chú");
        jButton17.setFocusable(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton17.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(jButton17);

        jLabel4.setText("Khách hàng:");

        jTextField2.setBackground(new java.awt.Color(255, 255, 153));

        tbl_mathang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mặt hàng", "SL", "Đơn giá", "Thành tiền", "ĐVT", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_mathang1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_mathang1.setPreferredSize(new java.awt.Dimension(440, 450));
        jScrollPane3.setViewportView(tbl_mathang1);
        if (tbl_mathang1.getColumnModel().getColumnCount() > 0) {
            tbl_mathang1.getColumnModel().getColumn(0).setPreferredWidth(170);
            tbl_mathang1.getColumnModel().getColumn(1).setPreferredWidth(50);
            tbl_mathang1.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbl_mathang1.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel4.setBackground(new java.awt.Color(187, 206, 230));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Tiền hàng:");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("0.0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Giảm giá:");

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField7.setText("5%");

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setText("0.0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Tổng cộng:");

        jTextField9.setBackground(new java.awt.Color(255, 255, 153));
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(255, 51, 51));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField9.setText("0.0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8))
                            .addComponent(jTextField9))))
                .addGap(124, 124, 124))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Số tiền", jPanel4);
        jTabbedPane2.addTab("Ghi chú", jTextField3);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/quanlybanhang.png"))); // NOI18N

        javax.swing.GroupLayout panel_hoadonLayout = new javax.swing.GroupLayout(panel_hoadon);
        panel_hoadon.setLayout(panel_hoadonLayout);
        panel_hoadonLayout.setHorizontalGroup(
            panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(panel_hoadonLayout.createSequentialGroup()
                .addGroup(panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_hoadonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_hoadonLayout.createSequentialGroup()
                        .addGroup(panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_hoadonLayout.createSequentialGroup()
                                .addComponent(cbc_gio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_moban))
                            .addGroup(panel_hoadonLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(85, 85, 85)
                        .addGroup(panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_hoadonLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbc_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_hoadonLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jTextField1)))))
                .addGap(2, 2, 2))
        );
        panel_hoadonLayout.setVerticalGroup(
            panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hoadonLayout.createSequentialGroup()
                .addGroup(panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbc_gio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_moban)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbc_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_hoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        panel_button.setBackground(new java.awt.Color(187, 206, 230));

        btn_chuyenban.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        btn_chuyenban.setText("Chuyển bàn");
        btn_chuyenban.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_chuyenban.setHideActionText(true);

        jButton9.setText("Gộp bàn");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Số lượng");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" }));

        btn_them.setText("<< Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_giam.setText("Giảm>>");

        btn_xoa.setText("Xóa");

        jButton19.setText("In chế biến");

        jButton20.setText("Thanh toán(F11)");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Thống kê(F9)");

        javax.swing.GroupLayout panel_buttonLayout = new javax.swing.GroupLayout(panel_button);
        panel_button.setLayout(panel_buttonLayout);
        panel_buttonLayout.setHorizontalGroup(
            panel_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_giam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_chuyenban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_buttonLayout.setVerticalGroup(
            panel_buttonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_buttonLayout.createSequentialGroup()
                .addComponent(btn_chuyenban, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_giam, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        panel_khuvuc.setBackground(new java.awt.Color(187, 206, 230));

        javax.swing.GroupLayout panel_khuvucLayout = new javax.swing.GroupLayout(panel_khuvuc);
        panel_khuvuc.setLayout(panel_khuvucLayout);
        panel_khuvucLayout.setHorizontalGroup(
            panel_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_khuvucLayout.createSequentialGroup()
                .addGroup(panel_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tab_khuvucduoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tab_khuvuctren, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_khuvucLayout.setVerticalGroup(
            panel_khuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_khuvucLayout.createSequentialGroup()
                .addComponent(tab_khuvuctren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab_khuvucduoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout sudungdichvuLayout = new javax.swing.GroupLayout(sudungdichvu);
        sudungdichvu.setLayout(sudungdichvuLayout);
        sudungdichvuLayout.setHorizontalGroup(
            sudungdichvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sudungdichvuLayout.createSequentialGroup()
                .addComponent(panel_khuvuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_hoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_mathang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(731, Short.MAX_VALUE))
        );
        sudungdichvuLayout.setVerticalGroup(
            sudungdichvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sudungdichvuLayout.createSequentialGroup()
                .addGroup(sudungdichvuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_hoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_mathang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panel_khuvuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tab_chinh.addTab("Sử dụng dịch vụ", sudungdichvu);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar3.setBackground(new java.awt.Color(204, 204, 204));
        jToolBar3.setRollover(true);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton23.setFocusable(false);
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton23);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/office-material.png"))); // NOI18N
        jButton24.setFocusable(false);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(jButton24);
        jToolBar3.add(jSeparator1);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        jButton28.setFocusable(false);
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(jButton28);

        list_KhuVuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        list_KhuVuc.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "--Tất cả", "--Khu A", "--Khu B", "--Khu C", "--Khu D", "--Thùng rác" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(list_KhuVuc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
            .addComponent(jScrollPane5)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Khu vực", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar5.setRollover(true);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton25.setText("Thêm mới(Insert)  ");
        jButton25.setFocusable(false);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jToolBar5.add(jButton25);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        jButton26.setText("Chỉnh sửa(F4)   ");
        jButton26.setFocusable(false);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar5.add(jButton26);

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        jButton27.setText("Xóa(Del)");
        jButton27.setFocusable(false);
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar5.add(jButton27);
        jToolBar5.add(jSeparator3);

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        jButton29.setFocusable(false);
        jButton29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton29.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar5.add(jButton29);

        jLabel11.setText("Lọc dữ liệu(F3):  ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã bàn", "Tên bàn", "Khu vực"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar5, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bàn", jPanel2);

        javax.swing.GroupLayout danhmucbankhuvucLayout = new javax.swing.GroupLayout(danhmucbankhuvuc);
        danhmucbankhuvuc.setLayout(danhmucbankhuvucLayout);
        danhmucbankhuvucLayout.setHorizontalGroup(
            danhmucbankhuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhmucbankhuvucLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3))
        );
        danhmucbankhuvucLayout.setVerticalGroup(
            danhmucbankhuvucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jTabbedPane3)
        );

        tab_chinh.addTab("Danh mục bàn, khu vực", danhmucbankhuvuc);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar6.setRollover(true);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton30.setText("Thêm mới(Insert)  ");
        jButton30.setFocusable(false);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton30.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton30);

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/office-material.png"))); // NOI18N
        jButton31.setText("Chỉnh sửa(F4)   ");
        jButton31.setFocusable(false);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton31.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar6.add(jButton31);

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        jButton32.setText("Xóa(Del)");
        jButton32.setFocusable(false);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton32.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar6.add(jButton32);
        jToolBar6.add(jSeparator4);

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        jButton33.setFocusable(false);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton33.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar6.add(jButton33);

        jLabel12.setText("Lọc dữ liệu(F3):  ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Nhóm sản phẩm", "Đơn vị tính", "Giá bán", "Giá nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar6, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Sản phẩm", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar4.setRollover(true);

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton34.setFocusable(false);
        jButton34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton34.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton34);

        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/office-material.png"))); // NOI18N
        jButton35.setFocusable(false);
        jButton35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton35.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar4.add(jButton35);
        jToolBar4.add(jSeparator5);

        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        jButton36.setFocusable(false);
        jButton36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton36.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar4.add(jButton36);

        list_NhomSP.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "--Tất cả", "--Trà", "--Sinh tố", "--Kem", "--Nước ép hoa quả", "--Nước hoa quả", "--Sữa", "--Cafe-Cacao", "--Thùng rác" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(list_NhomSP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
            .addComponent(jScrollPane7)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Nhóm sản phẩm", jPanel6);

        javax.swing.GroupLayout danhmucnhomsanphamLayout = new javax.swing.GroupLayout(danhmucnhomsanpham);
        danhmucnhomsanpham.setLayout(danhmucnhomsanphamLayout);
        danhmucnhomsanphamLayout.setHorizontalGroup(
            danhmucnhomsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhmucnhomsanphamLayout.createSequentialGroup()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4))
        );
        danhmucnhomsanphamLayout.setVerticalGroup(
            danhmucnhomsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
            .addComponent(jTabbedPane4)
        );

        tab_chinh.addTab("Quản lý sản phẩm", danhmucnhomsanpham);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar8.setRollover(true);

        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/add.png"))); // NOI18N
        jButton40.setText("Thêm mới(Insert)  ");
        jButton40.setFocusable(false);
        jButton40.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton40.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jToolBar8.add(jButton40);

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/office-material.png"))); // NOI18N
        btn_sua.setText("Chỉnh sửa(F4)   ");
        btn_sua.setFocusable(false);
        btn_sua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_sua.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jToolBar8.add(btn_sua);

        btn_XoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/delete.png"))); // NOI18N
        btn_XoaKH.setText("Xóa(Del)");
        btn_XoaKH.setFocusable(false);
        btn_XoaKH.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_XoaKH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_XoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaKHActionPerformed(evt);
            }
        });
        jToolBar8.add(btn_XoaKH);
        jToolBar8.add(jSeparator7);

        btn_loadData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/exchange.png"))); // NOI18N
        btn_loadData.setFocusable(false);
        btn_loadData.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_loadData.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_loadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loadDataActionPerformed(evt);
            }
        });
        jToolBar8.add(btn_loadData);

        jLabel13.setText("Lọc dữ liệu(F3):  ");

        txt_timkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timkiemCaretUpdate(evt);
            }
        });

        tbl_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Khách Hàng", "Tên khách", "Địa chỉ", "Số điện thoại", "Email", "Fax", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbl_KhachHang);
        if (tbl_KhachHang.getColumnModel().getColumnCount() > 0) {
            tbl_KhachHang.getColumnModel().getColumn(0).setPreferredWidth(5);
            tbl_KhachHang.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(531, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jToolBar8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9))
        );

        jTabbedPane7.addTab("Khách hàng", jPanel9);

        javax.swing.GroupLayout danhmuckhachangLayout = new javax.swing.GroupLayout(danhmuckhachang);
        danhmuckhachang.setLayout(danhmuckhachangLayout);
        danhmuckhachangLayout.setHorizontalGroup(
            danhmuckhachangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhmuckhachangLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane7))
        );
        danhmuckhachangLayout.setVerticalGroup(
            danhmuckhachangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane7)
        );

        tab_chinh.addTab("Quản lý khách hàng", danhmuckhachang);

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/sudungdichvu.png"))); // NOI18N
        jButton1.setText("Sử dụng dịch vụ");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/quanlybanhang.png"))); // NOI18N
        jButton2.setText("Quản lý sản phẩm");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/doanhthu.png"))); // NOI18N
        jButton3.setText("Doanh thu");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/thongke.png"))); // NOI18N
        jButton4.setText("Thống kê mặt hàng");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/tonkho.png"))); // NOI18N
        jButton5.setText("Tồn kho");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ghichu.png"))); // NOI18N
        jButton6.setText("Ghi chú nhanh");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/baocao.png"))); // NOI18N
        jButton7.setText("Báo cáo");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton7.setMargin(new java.awt.Insets(2, 0, 2, 10));
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/thoat.png"))); // NOI18N
        jButton8.setText("Thoát");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton8);

        jMenuBar1.setBackground(new java.awt.Color(187, 206, 230));
        jMenuBar1.setBorder(null);

        jMenu1.setBackground(new java.awt.Color(187, 206, 230));
        jMenu1.setText("HỆ THỐNG ");

        jMenuItem1.setText("Đổi mật khẩu");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(187, 206, 230));
        jMenu2.setText("HOẠT ĐỘNG");
        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(187, 206, 230));
        jMenu3.setText("KHO HÀNG");
        jMenuBar1.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(187, 206, 230));
        jMenu4.setText("QUỸ");
        jMenuBar1.add(jMenu4);

        jMenu5.setBackground(new java.awt.Color(187, 206, 230));
        jMenu5.setText("NHÂN SỰ");

        jMenuItem2.setText("Nhân viên");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(187, 206, 230));
        jMenu6.setText("QUẢN TRỊ");
        jMenuBar1.add(jMenu6);

        jMenu7.setBackground(new java.awt.Color(187, 206, 230));
        jMenu7.setText("TRỢ GIÚP");
        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_chinh)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(tab_chinh))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        DoiMatKhau dmk = new DoiMatKhau();
//        dmk.setVisible(true);
//        dmk.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
//        Nhanvien nv = new Nhanvien();
//        nv.setVisible(true);
//        nv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        _ChiTietKhachHang = new ChiTietKhachHang(this);
        _ChiTietKhachHang.setVisible(true);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        _ChiTietNhomSanPham = new ChiTietNhomSanPham(this);
        _ChiTietNhomSanPham.setVisible(true);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        _ChitietSanPham = new ChitietSanPham(this);
        _ChitietSanPham.setVisible(true);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        _ChiTietBan = new ChiTietBan(this);
        _ChiTietBan.setVisible(true);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        _ChiTietKhuVuc = new ChiTietKhuVuc(this);
        _ChiTietKhuVuc.setVisible(true);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //        Thanhtoan tt = new Thanhtoan();
        //        tt.setVisible(true);
        //        tt.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void list_douongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_douongMouseClicked
        int index = list_douong.getSelectedIndex();
        if (index == 0) {
            loadTableSanPham(_SanPhamService.selectAll(0, 100));
            return;
        }
        Long maNH = _NhomSanPhamService.getListFromDB().get(index - 1).getId();
        List<ModelSanPham> list = this._SanPhamService.findList(String.valueOf(maNH));
        _DefaultTableModel = (DefaultTableModel) tbl_mathang.getModel();
        _DefaultTableModel.setRowCount(0);
        if (list == null) {
            return;
        }
        for (ModelSanPham x : list) {
            _DefaultTableModel.addRow(new Object[]{
                x.getTenSanPham(), x.getDVT().getTenDVT(), x.getDonGia()
            });
        }
    }//GEN-LAST:event_list_douongMouseClicked

    private void btn_mobanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mobanActionPerformed
         _labelAnh.setIcon(new ImageIcon("src\\main\\resources\\ImageLogin\\" + _duongDanAnh));
        btn_moban.setEnabled(false);
        for (ModelBan x : _BanService.getListFromDB()) {
            if (x.getTenBan().equals(_labelTenBan.getText())) {
                x.setTrangThai(1);
                _BanService.update(x);
            }
        }
        setNgayGio();
    }//GEN-LAST:event_btn_mobanActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
       int index = tbl_mathang.getSelectedRow();
       
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_XoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaKHActionPerformed
        int index = tbl_KhachHang.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn khách hàng muốn xoá????");
            return;
        }
        int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá không !!!!!","XÁC NHẬN", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
           long id = _khachHangIService.getListFromDB().get(index).getId();
           long b = _khachHangIService.delete(id);
            if (b >= 0) {
                JOptionPane.showMessageDialog(this, "Xoá thành công!!!");
                loadDateKhachHang(_khachHangIService.selectAll(_currentPage-1, _pageSize));
            }else{
                JOptionPane.showMessageDialog(this, "Xoá thất bại!!!!!");
            }
        }
    }//GEN-LAST:event_btn_XoaKHActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        int index = tbl_KhachHang.getSelectedRow();
        if (index >= 0 ) {
           _ChiTietKhachHang = new ChiTietKhachHang(this);
           _ChiTietKhachHang.setVisible(true);
           _ChiTietKhachHang.loadDate1(_khachHangIService.getListFromDB().get(index));
        }else{
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng muốn thêm!!!!");
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void txt_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timkiemCaretUpdate
        List<ModelKhachHang> listKhachHangs = _khachHangIService.selectAll(0, 20);
        String text = txt_timkiem.getText();
        if (text.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin muốn tìm");
            return;
        }
        List<ModelKhachHang> lstKhachHang = new ArrayList<>();
        for (ModelKhachHang x : listKhachHangs) {
            if (x.getTenKhachHang().contains(text) || x.getDienThoai().contains(text)) {
                lstKhachHang.add(x);
            }
        }
        loadDateKhachHang(lstKhachHang);
    }//GEN-LAST:event_txt_timkiemCaretUpdate

    private void btn_loadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadDataActionPerformed
        txt_timkiem.setText("");
        loadDateKhachHang(_khachHangIService.selectAll(0, 20));
    }//GEN-LAST:event_btn_loadDataActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            Properties props = new Properties();
            props.put("logoString", "");
            Font f = new Font(Font.SERIF, Font.PLAIN, 12);
            Font f1 = new Font(Font.DIALOG, Font.PLAIN, 12);
            UIManager.put("TabbedPane.background", new Color(187, 206, 230));
            UIManager.put("Label.font", f1);
            UIManager.put("Button.font", f1);
            UIManager.put("RadioButton.font", f);
            LunaLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_XoaKH;
    private javax.swing.JButton btn_chuyenban;
    private javax.swing.JButton btn_giam;
    private javax.swing.JButton btn_loadData;
    private javax.swing.JButton btn_moban;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cbc_gio;
    private javax.swing.JComboBox<String> cbc_ngay;
    private javax.swing.JPanel danhmucbankhuvuc;
    private javax.swing.JPanel danhmuckhachang;
    private javax.swing.JPanel danhmucnhomsanpham;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JToolBar jToolBar5;
    private javax.swing.JToolBar jToolBar6;
    private javax.swing.JToolBar jToolBar8;
    private javax.swing.JList<String> list_KhuVuc;
    private javax.swing.JList<String> list_NhomSP;
    private javax.swing.JList<String> list_douong;
    private javax.swing.JPanel panel_button;
    private javax.swing.JPanel panel_hoadon;
    private javax.swing.JPanel panel_khuvuc;
    private javax.swing.JPanel panel_mathang;
    private javax.swing.JPanel sudungdichvu;
    private javax.swing.JTabbedPane tab_chinh;
    private javax.swing.JTabbedPane tab_khuvucduoi;
    private javax.swing.JTabbedPane tab_khuvuctren;
    private javax.swing.JTable tbl_KhachHang;
    private javax.swing.JTable tbl_mathang;
    private javax.swing.JTable tbl_mathang1;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
