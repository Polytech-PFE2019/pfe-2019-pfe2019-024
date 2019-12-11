package businessLevel;

public class UserVariable {

	private String nom;

	public UserVariable(String nom, String type) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.type = type;
	}

	private String type;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
