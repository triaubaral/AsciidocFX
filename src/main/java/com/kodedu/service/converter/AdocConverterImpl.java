package com.kodedu.service.converter;

import java.io.File;

import org.asciidoctor.Asciidoctor;

class AdocConverterImpl implements AdocConverter {
	
	private Asciidoctor asciidoctor;
	
	public AdocConverterImpl() {
		asciidoctor =  Asciidoctor.Factory.create();
	}
	
	@Override
	public String convert(String content, OptionsBuilder builder) {
		
		//OptionsBuilder.options().backend("pdf");
		
		return asciidoctor.convert(content, builder.asMap());
		/*return asciidoctor.convert(content, 
				org.asciidoctor.OptionsBuilder.options()
				.baseDir(baseDir)
				.toFile(new File("toto.pdf"))
				.backend("pdf"));*/
	}

}
