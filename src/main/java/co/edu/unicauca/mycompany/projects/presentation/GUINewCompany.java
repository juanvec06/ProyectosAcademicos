
package co.edu.unicauca.mycompany.projects.presentation;

import co.edu.unicauca.mycompany.projects.access.CompanySqliteRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import co.edu.unicauca.mycompany.projects.domain.services.CompanyService;
import co.edu.unicauca.mycompany.projects.infra.Messages;
import javax.swing.JFrame;

/**
 *
 * @author Libardo, Julio
 */
public class GUINewCompany extends javax.swing.JDialog {
    private CompanyService companyService;
    /**
     * Creates new form GUINewCompany
     * @param parent
     */
    public GUINewCompany(JFrame parent, CompanyService service) {
        super(parent, "Nueva Empresa", true); //true: modal
        this.companyService = service;
        
        initComponents();
        setSize(600,500);
        setLocationRelativeTo(parent);
        fillSectors();
    }

    private void fillSectors(){
        cboSector.removeAllItems();
        for(Sector sector: Sector.values()){
            cboSector.addItem(sector.toString());
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

        jLabel1 = new javax.swing.JLabel();
        pnlSouth = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPageWeb = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboSector = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registrar nueva Empresa");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlSouth.add(btnSave);

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        pnlSouth.add(btnClose);

        getContentPane().add(pnlSouth, java.awt.BorderLayout.PAGE_END);

        pnlCenter.setLayout(new java.awt.GridLayout(8, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("*Nit:");
        pnlCenter.add(jLabel2);
        pnlCenter.add(txtNit);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("*Nombre:");
        pnlCenter.add(jLabel3);
        pnlCenter.add(txtName);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Teléfono de contacto:");
        pnlCenter.add(jLabel4);
        pnlCenter.add(txtPhone);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Página web:");
        pnlCenter.add(jLabel5);
        pnlCenter.add(txtPageWeb);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("*Sector industrial:");
        pnlCenter.add(jLabel6);

        cboSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlCenter.add(cboSector);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("*Email:");
        pnlCenter.add(jLabel7);
        pnlCenter.add(txtEmail);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("*Password:");
        pnlCenter.add(jLabel8);
        pnlCenter.add(txtPassword);
        pnlCenter.add(jLabel9);

        jLabel10.setText("*: Campos obligatorios");
        pnlCenter.add(jLabel10);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        CompanyService serv = new CompanyService(new CompanySqliteRepository());  
        String nit = txtNit.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String pageWeb = txtPageWeb.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String sectorStr = (String)cboSector.getSelectedItem();
        Sector sector = Sector.valueOf(sectorStr);
        companyService.saveCompany(new Company(nit, name, phone, pageWeb, sector, email, password));
    }//GEN-LAST:event_btnSaveActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboSector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlSouth;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtPageWeb;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
