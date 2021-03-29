package project;

public class Panier {
	
	private String Code;
	private String Prix;
	public Panier( String code, String prix) {
		super();
		
		Code = code;
		Prix = prix;
	}

	
	@Override
	public String toString() {
		return "Panier [Code=" + Code + ", Prix=" + Prix + "]";
	}


	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getPrix() {
		return Prix;
	}
	public void setPrix(String prix) {
		Prix = prix;
	}
	
}
