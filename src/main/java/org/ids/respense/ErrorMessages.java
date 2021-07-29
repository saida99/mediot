package org.ids.respense;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Champ obligatoire manquant."),
    RECORD_ALREADY_EXISTS("L'enregistrement existe déjà."),
    INTERNAL_SERVER_ERROR("Erreur interne du serveur."),
    NO_RECORD_FOUND("L'enregistrement avec l'identifiant fourni est introuvable.");
	
	 private String errorMessages;

	private ErrorMessages(String errorMessage) {
		this.errorMessages = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessages;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessages = errorMessage;
	}
	 
}
