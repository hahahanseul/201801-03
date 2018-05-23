package model;

import java.io.Serializable;

/**
 * @author Hanseul Kim
 * */
public class KindBean implements Serializable {
	private String id, name;

	public KindBean(String id, String name) {
		setId(id);
		setName(name);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
