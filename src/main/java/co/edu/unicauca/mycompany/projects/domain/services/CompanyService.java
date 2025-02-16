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
    
    public boolean saveCompany(Company newCompany ) {
        //Verificar campos obligatorios
        if(!(checkMandatoryCamp(newCompany.getNit(), "Debe agregar el Nit")
        &&checkMandatoryCamp(newCompany.getName(), "Debe agregar el Nombre")
        &&checkMandatoryCamp(newCompany.getSector().toString(), "Debe agregar el Sector")
        &&checkMandatoryCamp(newCompany.getEmail(), "Debe agregar el Email")
        &&checkMandatoryCamp(newCompany.getPassword(), "Debe agregar una contraseña")
        )){
            return false;
        }
        //Veficar email valido
        if(!(checkValidEmail(newCompany.getEmail()))){
            return false;
        }
        //Verificar contraseña valida
        if(!(chechPassword(newCompany.getPassword()))){
            return false;
        }
        return repository.save(newCompany);
    }
    private boolean checkMandatoryCamp(String camp, String warningMessage)
    {
        if (camp.equals("")){
            //Se utiliza front para mostrar errores en una clase que es de back, revisar esto
            Messages.showMessageDialog(warningMessage,"Atención");
            //txtNit.requestFocus();
            return false;
        }
        return true;
    }
    private boolean checkValidEmail(String email){
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(!(matcher.matches())){
            Messages.showMessageDialog("Ingrese un email valido","Atención");
            return false;
        }
        return true;
    }
    /**
     * verifica si una contraseña es valida, que tenga mas de 6 caracteres, al menos una mayuscula y un caracter especial
     * @param password la contraseña a validar
     * @return true si la contraseña es valida, false si no lo es
     */
    private boolean chechPassword(String password) {
        if(password.length() < 6 || !(containsMayus(password)) || !(password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>?/\\\\].*"))){
            Messages.showMessageDialog("La contraseña debe tener al menos 6 caracteres, una mayuscula y un caracter especial","Atención");
            return false;
        }
        return true;
    }
    /**
     * verifica si un string contiene al menos una mayuscula
     * @param str el string a verificar
     * @return true si contiene una mayuscula, false si no
     */
    private boolean containsMayus(String str){
        for (int i=0; i<str.length(); i++){
            if(java.lang.Character.isUpperCase(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
