package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación del repositorio con Sqlite
 *
 * @author Libardo, Julio
 */
public class CompanySqliteRepository implements ICompanyRepository {

    private Connection conn;

    public CompanySqliteRepository(){
        initDatabase();
    }
    
    @Override
    public boolean save(Company newCompany) {
        try {
            //Validate product
            if (newCompany == null || newCompany.getNit().isBlank() || newCompany.getName().isBlank() ||
                    newCompany.getSector() == null || newCompany.getEmail().isBlank() || newCompany.getPassword().isBlank()) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO Company ( Nit, Name, Phone, Pageweb, Sector, Email, Password) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCompany.getNit());
            pstmt.setString(2, newCompany.getName());
            pstmt.setString(3, newCompany.getPhone());
            pstmt.setString(4, newCompany.getPageWeb());
            pstmt.setString(5, newCompany.getSector().name());
            pstmt.setString(6, newCompany.getEmail());
            pstmt.setString(7, newCompany.getPassword());
            pstmt.executeUpdate();
            
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Provider.Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public List<Company> listAll() {
        List<Company> companies = new ArrayList<>();
        try {

            String sql = "SELECT Nit, Name, Phone, PageWeb, Sector, Email, Password FROM Company";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Company newCompany = new Company(rs.getString("Nit"),rs.getString("Name"),rs.getString("Phone"),
                rs.getString("PageWeb"), Sector.valueOf(rs.getString("Sector")), rs.getString("Email"),rs.getString("Password"));

                companies.add(newCompany);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Provider.Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
    }
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Company (\n"
                + "	Nit text NOT NULL,\n"
                + "	Name text NOT NULL,\n"
                + "	Phone text,\n"
                + "	Pageweb text,\n"
                + "	Sector text NOT NULL,\n"
                + "	Email text NOT NULL,\n"
                + "	Password text NOT NULL,\n"
                + "	PRIMARY KEY(Nit),\n"
                + "	CHECK (Sector IN ('TECHNOLOGY','HEALTH','EDUCATION','SERVICES','OTHER'))\n"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(Provider.Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void connect() {
        // Si se quiere guardar los datos a un archivo
        //String url = "jdbc:sqlite:./mydatabase.db";
        
        // Guarda los datos en memoria RAM
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(Provider.Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


}
