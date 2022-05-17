package com.ifms.edu.siaula3.models;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "usuario")
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotNull
    private String name;

    @CPF(message = "CPF Invalido")
    private String cpf;

    @Basic
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "A senha deve ser informada")
	@Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres")
	private String password;	

    @Max(value = 5)
    @Min(value = 0, message = "Deve ser maior que 0")
    private Double pontos;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public User() {

    }

    public User(
        String name, 
        String cpf, 
        Date birthDate, 
        String email, 
        String password, 
        Double pontos
    ) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.pontos = pontos;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getPontos() {
        return this.pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public Collection<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getFormattedDate(){
        return dateFormat.format(this.birthDate);
    }
}
