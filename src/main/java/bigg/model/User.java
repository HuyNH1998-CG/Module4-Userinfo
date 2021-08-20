package bigg.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class User implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 5, max = 45, message = "Must be within 5 to 45 char")
    private String firstname;
    @Size(min = 5, max = 45, message = "Must be within 5 to 45 char")
    private String lastname;
    private String phoneNumber;
    @Min(18)
    private int age;
    @Email
    private String email;

    public User() {
    }

    public User(String firstname, String lastname, String phoneNumber, int age, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String phone = user.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors,"phoneNumber","phone.empty");
        if (phone.length()>11 || phone.length()<10){
            errors.rejectValue("phoneNumber", "phone.length");
        }
        if (!phone.startsWith("0")){
            errors.rejectValue("phoneNumber", "phone.startsWith");
        }
        if (!phone.matches("(^[0-9]*$)")){
            errors.rejectValue("phoneNumber", "phone.matches");
        }
    }
}
