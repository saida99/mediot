package org.ids.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpecialiteRequest {


	@NotNull(message="le champ nom du spécialité ne doit pas être null !")
	private String nom;

	

}



