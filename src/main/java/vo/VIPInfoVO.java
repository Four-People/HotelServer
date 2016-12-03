package vo;

import objects.VIPInfo.VIPType;

public class VIPInfoVO {
	public enum VIPType {
		NORMAL, Enterprise
	};

	VIPType type;
	String info;

	public VIPInfoVO(VIPType type, String info) {
		this.type = type;
		this.info = info;
	}

	public VIPType getType() {
		return type;
	}

	public void setType(VIPType type) {
		this.type = type;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
