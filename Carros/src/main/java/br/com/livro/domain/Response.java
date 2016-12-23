package br.com.livro.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	private String status;
	private String msg;
	
	public Response() {}
	
	public static Response ok(String string){
		Response r = new Response();
		r.setStatus("OK");
		r.setMsg(string);
		return r;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
