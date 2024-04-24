package com.algoExpert.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Entity
public class Assignee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer assignee_id;
	private Integer member_id;
	private Integer task_id;
	private String username;
	public Assignee(String username) {
		this.username = username;
	}
}
