package com.kodedu.service.converter;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public interface AdocConverter {
	
	//String convert(String content, OptionsBuilder builder);
	
	String convertFile(File file);
	
	String convertContentToFile(String fileContent, File ouputDir);
	
	enum Backend{
		PDF, HTML, DOCBOOK, EPUB3;
		
		public String getExtFile() {
			
			if(this.name().equals(DOCBOOK.toString())) {
				return ".xml";
			}
			
			if(this.name().equals(EPUB3.toString())) {
				return ".epub";
			}
			
			return "."+this.name().toLowerCase();
			
		}
	}
	
	class Factory {
		
		public static AdocConverter create(Backend backend) {
			
			if(backend == null) {
				throw new IllegalArgumentException("Backend parameter cannot be null !!");
			}
			
			if(backend.equals(Backend.PDF)) {
				return new PDFConverter() ;
			}
			
			if(backend.equals(Backend.HTML)) {
				return new HTMLConverter() ;
			}
			
			if(backend.equals(Backend.DOCBOOK)) {
				return new DocbookConverter() ;
			}
			
			if(backend.equals(Backend.EPUB3)) {
				return new EPUB3Converter() ;
			}
			
			throw new IllegalArgumentException("Converter for "+backend.name()+" output is not implemented.");
		}
	}
	
	/*class OptionsBuilder {
		
		private Options options = new Options();
		
		private OptionsBuilder() {
			super();
		}
		
		public static OptionsBuilder options() {
			return new OptionsBuilder();			
		}
		
		public OptionsBuilder backend(String backend) {
			
			options.setBackend(backend);
			
			return this;
		}
		
		public OptionsBuilder basedir(String basedir) {
			
			options.setBasedir(basedir);
			
			return this;
		}
		
		public OptionsBuilder toFile(File dirPath) {
			
			options.setFile(dirPath);
			
			return this;
		}
		
		public Map<String, Object> asMap() {
			return options.getMap();
		}
		
		private static class Options{
			
			private Map<String, Object> map = new HashMap<>();
			
			public Options() {
				map.put("to_file", true);
			}
			
			public enum Key{
				BACKEND, BASEDIR, TO_FILE;
				
				public String getName() {
					
					return this.name().toLowerCase();
				}
			}			
			
			public void setBackend(String backend) {
				map.put(Key.BACKEND.getName(), backend);
			}			
			public void setBasedir(String basedir) {
				map.put(Key.BASEDIR.getName(), basedir);
			}
			
			public void setFile(File path) {				
				map.put(Key.TO_FILE.getName(), path);
				
			}
			
			public Object get(Key key) {
				return map.get(key.getName());
			}
			
			public Map<String, Object> getMap() {
				return map;
			}
			
			
		}
		
	}*/
	
	

}
