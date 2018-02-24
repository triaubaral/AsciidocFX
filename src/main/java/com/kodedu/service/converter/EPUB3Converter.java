package com.kodedu.service.converter;

import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;

class EPUB3Converter extends DefaultAdocConverter {
	
	public EPUB3Converter() {		
		//Safemode is compulsory for epub3 format because 
		//of a known bug inside  asciidoctorj-epub3/1.5.0-alpha.4 
		optionsBuilder = OptionsBuilder.options().safe(SafeMode.SAFE).backend("epub3");
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
