package com.ibm.creditcard.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

	private Long id;

	@NotNull(message = "La tarjeta sugerida no puede ser nulo")
	@NotEmpty(message = "La tarjeta sugerida no pude estar vacia")
	private String suggestedCard;

	@NotNull(message = "La passion no puede ser nulo")
	@NotEmpty(message = "La passion no pude estar vacia")
	private String userPassion;
	
	@NotNull(message = "El salary no puede ser nulo")
	@NotEmpty(message = "El salary no pude estar vacia")
	private Double userSalary;
	
	@NotNull(message = "El age no puede ser nulo")
	@NotEmpty(message = "El age no pude estar vacia")
	private Integer userAge;

	@NotNull(message = "El salaryFrom no puede ser nulo")
	@NotEmpty(message = "El salaryFrom no pude estar vacia")
	private Double salaryFrom;

	@NotNull(message = "El salaryto no puede ser nulo")
	@NotEmpty(message = "El salaryto no pude estar vacia")
	private Double salaryTo;
	
	@NotNull(message = "El ageTo  no puede ser nulo")
	@NotEmpty(message = "La ageTo no pude estar vacia")
	private Integer ageTo;

	@NotNull(message = "El ageFrom  no puede ser nulo")
	@NotEmpty(message = "El ageFrom no pude estar vacia")
	private Integer ageFrom;

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id=");
		builder.append(id);
		builder.append(", Tarjeta sugerida=");
		builder.append(suggestedCard);
		builder.append(", Passion del usuario=");
		builder.append(userPassion);
		builder.append(", Salario del usuario=");
		builder.append(userSalary);
		builder.append(", Edad del usuario=");
		builder.append(userAge);
		builder.append("Requisitos de tarjeta");
		builder.append(", Salario minimo=");
		builder.append(salaryFrom);
		builder.append(", Salario maximo=");
		builder.append(salaryTo);
		builder.append(", Edad minima=");
		builder.append(ageTo);
		builder.append(", Edad maxima=");
		builder.append(ageFrom);

		return builder.toString();
	}
	
	
	

}
