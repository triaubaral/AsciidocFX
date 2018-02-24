package com.kodedu.service.converter;

import java.io.File;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.OptionsBuilder;

abstract class DefaultAdocConverter implements AdocConverter {
	
	private Asciidoctor asciidoctor;

	protected OptionsBuilder optionsBuilder;
	
	public DefaultAdocConverter() {
		asciidoctor =  Asciidoctor.Factory.create();
	}

	@Override
	public String convertFile(File fileContent) {
		
		optionsBuilder.baseDir(fileContent.getParentFile());		
		String filePath = fileContent.getAbsolutePath();
		String extensionFile = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
		String backend = (String) optionsBuilder.asMap().get(Options.BACKEND);
		filePath = filePath.replace(extensionFile, Backend.valueOf(backend.toUpperCase()).getExtFile());		
		optionsBuilder.toFile(new File(filePath));
		
		return asciidoctor.convertFile(fileContent, optionsBuilder);
			
	}

	@Override
	public String convertContentToFile(String fileContent, File outputPath) {
				
		optionsBuilder.baseDir(outputPath.getParentFile());
		optionsBuilder.toFile(outputPath);
		
		return asciidoctor.convert(fileContent, optionsBuilder);
	}

}
