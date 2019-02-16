package cj.studio.backend.uacc.security;

public class Relactionship {
	String colName;
	RshipType type;
	public Relactionship() {
		type=RshipType.manyToMany;
	}
	public Relactionship(String colName, RshipType type) {
		this.colName = colName;
		this.type = type;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public RshipType getType() {
		return type;
	}
	public void setType(RshipType type) {
		this.type = type;
	}
	
}
