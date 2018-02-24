package com.kodedu.service.converter;

import org.asciidoctor.OptionsBuilder;

class DocbookConverter extends DefaultAdocConverter {
	
	public DocbookConverter() {			
		optionsBuilder = OptionsBuilder.options().backend("docbook");
	}
		
	
	
	
	
	
	
//	public String converthghg(String content, OptionsBuilder builder) {
//		
//		//OptionsBuilder.options().backend("pdf");
//		
//		//return asciidoctor.convert(content, builder.asMap());
//		return asciidoctor.convert(content, 
//				org.asciidoctor.OptionsBuilder.options()
//				.baseDir(baseDir)
//				.toFile(new File("toto.pdf"))
//				.backend("pdf"));
//		
//		return "";
//	}

}
