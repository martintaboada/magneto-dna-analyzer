package ar.com.mtaboada.magnetodnaanalyzer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mtaboada
 *
 */
@RestController
public class EndPointController {

	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
	public void analyze() {
	}

}
