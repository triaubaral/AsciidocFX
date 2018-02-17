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
		PDF, HTML;
	}
	
	class Factory {
		
		public static AdocConverter create(Backend backend) {
			
			if(backend.equals(Backend.PDF)) {
				return new PDFConverter() ;
			}
			
			return new PDFConverter() ;
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
