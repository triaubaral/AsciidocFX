package com.kodedu.service.converter;

import org.asciidoctor.OptionsBuilder;

class HTMLConverter extends DefaultAdocConverter {
	
	public HTMLConverter() {	
		optionsBuilder = OptionsBuilder.options().backend("html");
	}

}
