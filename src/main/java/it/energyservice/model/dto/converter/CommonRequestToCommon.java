package it.energyservice.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.energyservice.exception.ProvinceNotFoundException;
import it.energyservice.model.Common;
import it.energyservice.model.Province;
import it.energyservice.model.dto.common.CommonRequest;
import it.energyservice.service.ProvinceService;

@Component
public class CommonRequestToCommon implements Converter<CommonRequest, Common> {

	@Autowired
	private ProvinceService provinceService;

	@Override
	public Common convert(CommonRequest source) {
		Common target = new Common();

		target.setNome(source.getNome());
		// target.setProvincia(provinceService.findById(source.getProvincia().getId()));
		Province province = provinceService.findProvinceByName(source.getProvincia().getNome());
		if(province != null) {
			target.setProvincia(province);
		} else {
			throw new ProvinceNotFoundException("No provinces found with this name");
		}

		return target;
	}

}
