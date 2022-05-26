package com.code.social.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostRequest {

	@NotBlank
	@Size(min = 10)
	private String title;

	@NotBlank
	@Size(min = 50)
	private String description;

	public PostRequest() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
