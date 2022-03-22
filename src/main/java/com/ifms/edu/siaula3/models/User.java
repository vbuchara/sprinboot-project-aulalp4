package com.ifms.edu.siaula3.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import org.springframework.format.annotation.DateTimeFormat;

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

    @Max(value = 5)
    @Min(value = 0, message = "Deve ser maior que 0")
    private Double ponto;
}
