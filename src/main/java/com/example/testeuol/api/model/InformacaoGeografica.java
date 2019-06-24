package com.example.testeuol.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InformacaoGeografica {

	private String status;
	private Value data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Value getData() {
		return data;
	}

	public void setData(Value data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "InformacaoGeografica {status=" + status + ", data=" + data + "}";
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Value {

		private String ipv4;
		private String latitude;
		private String longitude;

		public String getIpv4() {
			return ipv4;
		}

		public void setIpv4(String ipv4) {
			this.ipv4 = ipv4;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		@Override
		public String toString() {
			return "Value {ipv4=" + ipv4 + ", latitude=" + latitude + ", longitude=" + longitude + "}";
		}

	}
}
