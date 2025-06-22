package healthy_recipe.healthy_recipe;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.print.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * Kelas `PemberianObat` adalah antarmuka pengguna (GUI) untuk staf farmasi
 * guna mengelola (CRUD: Create, Read, Update, Delete) dan mencetak resep obat.
 * Aplikasi ini menampilkan riwayat resep obat dalam tabel, memungkinkan pencarian,
 * serta menyediakan form dan tombol untuk menambah, mengedit, dan menghapus resep.
 *
 * Kelas ini juga mendefinisikan inner class `ResepPrinter` untuk fungsionalitas pencetakan
 * dan `ResepObatData` sebagai model data resep yang mendukung konsep pewarisan dan polimorfisme.
 */
public class PemberianObat extends javax.swing.JFrame {
     Connection con;
 /**
     * Inner class `ResepPrinter` mengimplementasikan interface `Printable`
     * untuk menangani proses pencetakan resep obat.
     */    
class ResepPrinter implements Printable {
    private String isiResep;
/**
         * Konstruktor untuk `ResepPrinter`.
         * @param isiResep String yang berisi data resep yang telah diformat.
         */
    public ResepPrinter(String isiResep) {
        this.isiResep = isiResep;
    }
/**
         * Mengimplementasikan metode `print` dari interface `Printable`.
         * @param g Objek `Graphics` yang digunakan untuk menggambar.
         * @param pf Objek `PageFormat` yang mendeskripsikan ukuran dan orientasi halaman.
         * @param pageIndex Indeks halaman yang akan dicetak (dimulai dari 0).
         * @return `Printable.PAGE_EXISTS` jika halaman berhasil dicetak, atau `Printable.NO_SUCH_PAGE` jika
         * indeks halaman di luar batas.
         */
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        g.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Cetak setiap baris isiResep
        int y = 20;
        for (String line : isiResep.split("\n")) {
            g.drawString(line, 10, y);
            y += 15;
        }

        return PAGE_EXISTS;
    }
}
/**
     * Inner class `ResepObatData` mewakili satu entri resep obat,
     * yang mewarisi dari `ObatDasar` dan mengimplementasikan `PrintableData`.
     * Kelas ini mendemonstrasikan konsep inheritance, overloading (konstruktor),
     * overriding (metode `getDataFormatted`), dan enkapsulasi.
     */
class ResepObatData extends ObatDasar implements PrintableData {
    private String pasien, diagnosa, obat, dosis;

/**
         * Konstruktor untuk `ResepObatData` yang menginisialisasi semua detail resep.
         * Ini adalah contoh Constructor Overloading.
         *
         * @param pasien Nama pasien yang diresepkan.
         * @param diagnosa Diagnosa penyakit pasien.
         * @param obat Nama obat yang diresepkan.
         * @param dosis Dosis obat yang diresepkan.
         */  
    public ResepObatData(String pasien, String diagnosa, String obat, String dosis) {
        this.pasien = pasien;
        this.diagnosa = diagnosa;
        this.obat = obat;
        this.dosis = dosis;
    }
/**
         * Konstruktor kedua untuk `ResepObatData` yang hanya menginisialisasi
         * pasien dan diagnosa, dengan nilai default untuk obat dan dosis.
         * Ini adalah contoh Constructor Overloading.
         *
         * @param pasien Nama pasien.
         * @param diagnosa Diagnosa penyakit.
         */
    public ResepObatData(String pasien, String diagnosa) {
    this.pasien = pasien;
    this.diagnosa = diagnosa;
    this.obat = "Belum ada";  
    this.dosis = "Belum ditentukan"; 
}
   public String getPasien() {
        return pasien;
    }

    public void setPasien(String pasien) {
        this.pasien = pasien;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

 /**
         * Mengimplementasikan dan meng-override metode `getDataFormatted()`
         * dari interface `PrintableData`.
         * Memformat detail resep obat menjadi sebuah string yang mudah dibaca,
         *
         * @return String yang diformat berisi detail resep obat.
         */
    @Override
    public String getDataFormatted() {
        return "=== CETAK RESEP OBAT ===\n"
                + "Nama Pasien : " + pasien + "\n"
                + "Diagnosa    : " + diagnosa + "\n"
                + "Obat        : " + obat + "\n"
                + "Dosis       : " + dosis + "\n"
                + "==========================";
    }
}

/**
     * Konstruktor untuk membuat objek `PemberianObat` baru.
     * Menginisialisasi komponen GUI, mengatur posisi frame di tengah layar,
     * mengatur operasi penutupan frame agar hanya menutup frame ini (`DISPOSE_ON_CLOSE`),
     * membangun koneksi ke database, dan memuat data resep awal ke dalam tabel.
     */
    public PemberianObat() {
        super();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        connect(); 
        tampilkanData("");
    }
    /**
    * Membuat koneksi ke database menggunakan JDBC.
    * Pastikan konfigurasi URL, username, dan password sesuai.
     */
    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/healthy_recipe", "root", "");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + ex.getMessage());
        }
    }
 /**
 * Menampilkan data dari database ke tabel.
 * 
 * @param keyword Kata kunci untuk filter data (bisa kosong untuk tampilkan semua).
 */
 public void tampilkanData(String keyword){
     DefaultTableModel model = new  DefaultTableModel(
     new String[]{"Nama Pasien", "Diagnosa Penyakit","Nama Obat", "Dosis"},0);
     jTable1.setModel(model);

     String sql = "SELECT nama_pasien, diagnosa, nama_obat, dosis FROM resep_obat";
     if(keyword != null && !keyword .trim().isEmpty()){
         sql +=" WHERE nama_pasien LIKE?";
}
     try(PreparedStatement ps = con.prepareStatement(sql)){
         if(keyword != null && !keyword.trim(). isEmpty()){
             ps.setString(1, "%" + keyword + "%");
         }
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
             model.addRow(new Object[]{
                 rs.getString("nama_pasien"),
                 rs.getString("diagnosa"),
                 rs.getString("nama_obat"),
                 rs.getString("dosis")
             });  
         }
    }catch(SQLException ex){
        JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + ex.getMessage());
         
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel1.setText("Selamat Datang, Staff !");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Pasien", "Diagnosa Penyakit", "Nama Obat", "Dosis "
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Riwayat Resep Obat");

        jLabel3.setText("Cari nama pasien");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/farmasi.png"))); // NOI18N
        jLabel4.setText("jLabel4");

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setText("Cetak Resep");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 126, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Event handler untuk aksi pada `jTextField1` (kolom pencarian).
     * Metode ini dipanggil ketika pengguna menekan tombol Enter
     * setelah memasukkan teks di `jTextField1`. Ini akan memicu pembaruan
     * data di tabel berdasarkan kata kunci yang dimasukkan.
     *
     * @param evt Objek `ActionEvent` yang dihasilkan oleh aksi pengguna.
     */
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
     String keyword= jTextField1.getText();
     tampilkanData(keyword);
    }//GEN-LAST:event_jTextField1ActionPerformed
/**
     * Event handler untuk tombol "Cetak Resep" (`jButton1`).
     * Metode ini dipanggil ketika tombol cetak diklik.
     * Ini memeriksa apakah ada baris yang dipilih di `jTable1`.
     * Jika ada, data resep dari baris yang dipilih akan diambil, diformat,
     * dan dikirim ke printer. Pesan peringatan akan ditampilkan jika tidak ada
     * baris yang dipilih atau jika terjadi kesalahan selama pencetakan.
     *
     * @param evt Objek `ActionEvent` yang dihasilkan oleh klik tombol.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    int baris = jTable1.getSelectedRow();

    if (baris == -1) {
        JOptionPane.showMessageDialog(this, "Silakan pilih baris resep yang ingin dicetak!");
    } else {
        String pasien = jTable1.getValueAt(baris, 0).toString();
        String diagnosa = jTable1.getValueAt(baris, 1).toString();
        String obat = jTable1.getValueAt(baris, 2).toString();
        String dosis = jTable1.getValueAt(baris, 3).toString();

        PrintableData data = new ResepObatData(pasien, diagnosa, obat, dosis);
        String hasil = data.getDataFormatted();

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Cetak Resep");
        job.setPrintable(new ResepPrinter(hasil));

    boolean doPrint = job.printDialog();
    if (doPrint) {
        try {
            job.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencetak: " + ex.getMessage());
        }
    }
}
    }//GEN-LAST:event_jButton1ActionPerformed
/**
     * Metode main untuk menjalankan aplikasi.
     * Mengatur Look and Feel Swing ke "Nimbus" untuk tampilan yang lebih modern.
     * Kemudian, membuat instance baru dari `PemberianObat` dan menampilkannya
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PemberianObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PemberianObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PemberianObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PemberianObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PemberianObat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
