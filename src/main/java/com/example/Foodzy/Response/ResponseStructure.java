package com.example.Foodzy.Response;
public class ResponseStructure<T>{
	
		private int statuscode;
		private String message;
		private T data;
		public ResponseStructure(int statuscode, String message, T data) {
			super();
			this.statuscode = statuscode;
			this.message = message;
			this.data = data;
		}
		public ResponseStructure() {
			super();
		}
		public int getstatuscode() {
			return statuscode;
		}
		public void setstatuscode(int statuscode) {
			this.statuscode = statuscode;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return "ResponseStructure [statuscode=" + statuscode + ", message=" + message + ", data=" + data + "]";
		}
		
		


}