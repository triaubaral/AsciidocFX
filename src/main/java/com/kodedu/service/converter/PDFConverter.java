package com.kodedu.service.converter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;

class PDFConverter implements AdocConverter {
	
	private Asciidoctor asciidoctor;
	
	private OptionsBuilder optionsBuilder; 
	
	public PDFConverter() {
		
		asciidoctor =  Asciidoctor.Factory.create();	
		optionsBuilder = OptionsBuilder.options().backend("pdf");
	}
		
	
	@Override
	public String convertFile(File fileContent) {
		
		optionsBuilder.baseDir(fileContent.getParentFile());		
		String filePath = fileContent.getAbsolutePath();
		String extensionFile = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
		filePath = filePath.replace(extensionFile, ".pdf");		
		optionsBuilder.toFile(new File(filePath));
		
		return asciidoctor.convertFile(fileContent, optionsBuilder);
			
	}

	@Override
	public String convertContentToFile( String fileContent, File outputPath) {
				
		optionsBuilder.baseDir(outputPath.getParentFile());
		optionsBuilder.toFile(outputPath);
		
		return asciidoctor.convert(fileContent, optionsBuilder);
	}
	
	
	
//	@Override
//	public String convert(String content, OptionsBuilder builder) {
//		
//		//OptionsBuilder.options().backend("pdf");
//		
//		return asciidoctor.convert(content, builder.asMap());
//		/*return asciidoctor.convert(content, 
//				org.asciidoctor.OptionsBuilder.options()
//				.baseDir(baseDir)
//				.toFile(new File("toto.pdf"))
//				.backend("pdf"));*/
//	}

}
