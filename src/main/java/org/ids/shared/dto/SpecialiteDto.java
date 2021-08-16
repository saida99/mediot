package org.ids.shared.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class SpecialiteDto implements Serializable{

	private static final long serialVersionUID = 1677883997897642148L;
	
	private Long idSpecialite;
	private String nom;
	
	


}
