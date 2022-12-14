package cu.edu.cujae.backend.core.dto;

import java.sql.Timestamp;

public class VoterDto {
	
	private int numID;
	private String namVot;
	private String adressVot;
	private Timestamp birthdayVot;
	private int cdr;
	private int vote;
	private String cause;

	public VoterDto() {
		super();
	}
	public VoterDto(int numID, String namVot, Timestamp birthdayVot, String adressVot, int cdr, int vote, String cause) {
		super();
		this.numID = numID;
		this.namVot = namVot;
		this.adressVot = adressVot;
		this.birthdayVot = birthdayVot;
		this.cdr = cdr;
		this.vote = vote;
		this.cause = cause;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	public int getCdr() {
		return cdr;
	}
	public void setCdr(int cdr) {
		this.cdr = cdr;
	}
	public int getNumID() {
		return numID;
	}
	public void setNumID(int numID) {
		this.numID = numID;
	}
	public String getNamVot() {
		return namVot;
	}
	public void setNamVot(String namVot) {
		this.namVot = namVot;
	}
	public String getAdrVot() {
		return adressVot;
	}
	public void setAdrVot(String adrVot) {
		this.adressVot = adrVot;
	}
	public Timestamp getDateVot() {
		return birthdayVot;
	}
	public void setDateVot(Timestamp dateVot) {
		this.birthdayVot = dateVot;
	}
}
