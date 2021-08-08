package org.ids.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

	@Entity

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString

	public class Message implements Serializable {

		private static final long serialVersionUID = -1814602136747224775L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(unique = true, nullable = false)
		private int idMessage;
		@Column(nullable = false)
		private Date dateEnvoi;
		@Column(nullable = false)
		private String contenu;
		
		@OneToOne
		private Patient  patient;
		
		@OneToOne(mappedBy = "message")
		private RendezVous  rendezVous;

}
