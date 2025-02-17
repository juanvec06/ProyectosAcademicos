package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.infra.Messages;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public String saveCompany(Company newCompany) {
        // Verificar campos obligatorios
        String errorMsg = checkMandatoryFields(newCompany);
        if (errorMsg != null) return errorMsg;

        // Verificar email válido
        if (!checkValidEmail(newCompany.getEmail())) {
            return "Ingrese un email válido.";
        }

        // Verificar contraseña válida
        if (!checkPassword(newCompany.getPassword())) {
            return "La contraseña debe tener al menos 6 caracteres, una mayúscula y un carácter especial.";
        }

        // Guardar en el repositorio si pasa todas las validaciones
        boolean saved = repository.save(newCompany);
        return saved ? null : "Error al guardar la empresa.";
    }
    private String checkMandatoryFields(Company company) {
        if (company.getNit().isEmpty()) return "Debe agregar el Nit.";
        if (company.getName().isEmpty()) return "Debe agregar el Nombre.";
        if (company.getSector() == null) return "Debe agregar el Sector.";
        if (company.getEmail().isEmpty()) return "Debe agregar el Email.";
        if (company.getPassword().isEmpty()) return "Debe agregar una contraseña.";
        return null; // Todo está bien
    }

    private boolean checkValidEmail(String email) {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean checkPassword(String password) {
        return password.length() >= 6 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?/\\\\].*");
    }
}
