package it.energyservice.model.dto.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonRequest {

	private String name;
	private ProvinceRequest province;
}
