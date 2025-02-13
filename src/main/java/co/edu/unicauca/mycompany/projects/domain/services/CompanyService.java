package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.infra.Messages;
import java.util.List;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class CompanyService {

    private ICompanyRepository repository;

    public CompanyService(ICompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> getAllCompanies() {
        return repository.listAll();
    }

    public boolean saveCompany(Company newCompany) {
        if(!(checkMandatoryCamp(newCompany.getNit(), "Debe agregar el Nit")
        &&checkMandatoryCamp(newCompany.getName(), "Debe agregar el Nombre")
        &&checkMandatoryCamp(newCompany.getSector().toString(), "Debe agregar el Sector")
        &&checkMandatoryCamp(newCompany.getEmail(), "Debe agregar el Email")
        &&checkMandatoryCamp(newCompany.getPassword(), "Debe agregar una contraseña")
        )){
            return false;
        }
        return repository.save(newCompany);
    }
    private boolean checkMandatoryCamp(String camp, String warningMessage)
    {
        if (camp.equals("")){
            //Se utiliza front para mostrar errores en una clase que es de bach, revisar esto
            Messages.showMessageDialog(warningMessage,"Atención");
            //txtNit.requestFocus();
            return false;
        }
        return true;
    }
}
