package com.kodedu.adocj

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

import com.kodedu.service.converter.AdocConverter

import spock.lang.Ignore
import spock.lang.Specification

/*
 * 
    Migration from asciidoctor.js extensions to asciidoctorj extensions
    ** chart plugin, math plugin, plantuml plugin, ditaa plugin, tree plugin
    supporting reveal.js with asciidoctorj
    pdf plugin customization in UI
    switching option from asciidoctorj-pdf to fop by user
    asciidoctorj should be used in preview and for other backends, like html, epub
    Outline feature

 */

class AdocConverterSpec extends Specification{
	
	def File workingDir = new File("/home/coder/git/AsciidocFX-aurel");
	
	def "Generate document from DocWithoutPluginStatements.adoc file with default options"() {
		
		when: "DocWithoutPluginStatements.adoc file is loaded"
				
		File fileToProcess = getFileToProcess()
				
		then: "generate a pdf file."
		
		AdocConverter converter = AdocConverter.Factory.create(AdocConverter.Backend.PDF)
		
		String result = converter.convertFile(fileToProcess)
		
		and : "Check if the generated file exists"
		Paths.get(outputPath).toFile().exists() == true
		
		and : "Check if the generated file size is higher than zero byte"
		Paths.get(outputPath).toFile().text.length() > 0

		where:
		outputPath  = "target/test/DocWithoutPluginStatements.pdf";
		//name  << ["DocWithoutPluginStatements.pdf", "DocWithoutPluginStatements.html", "DocWithoutPluginStatements.ebook"]                            
		 
	}
	
	def File getFileToProcess() {
		
		File adocFileBackup = new File("./src/test/resources/DocWithoutPluginStatements.adoc")
						
		File adocFileToProcess = new File(workingDir.getPath()+"/target/test/"+adocFileBackup.getName())
		
		adocFileToProcess.mkdirs()
				
		Files.copy(Paths.get(adocFileBackup.getPath()), Paths.get(adocFileToProcess.getPath()), StandardCopyOption.REPLACE_EXISTING)
		
		adocFileToProcess 
		
	}
	
	
	def "Generate document from DocWithoutPluginStatements.adoc content with default options"() {
		
		when: "DocWithoutPluginStatements.adoc content is loaded"
		
		File fileToProcess = getFileToProcess()
				
		String adocContent = fileToProcess.getText()
		
		then: "generate a pdf file."
		
		AdocConverter converter = AdocConverter.Factory.create(AdocConverter.Backend.PDF)
		
		File output = new File(fileToProcess.getParent()+File.separator+"toto.pdf");
		
		String result = converter.convertContentToFile(adocContent, output)
		
		and : "Check if the generated file exists"
		Paths.get(outputPath).toFile().exists() == true
		
		and : "Check if the generated file size is higher than zero byte"
		Paths.get(outputPath).toFile().text.length() > 0

		where:
		outputPath  = "target/test/toto.pdf";
		////name  << ["DocWithoutPluginStatements.pdf", "DocWithoutPluginStatements.html", "DocWithoutPluginStatements.ebook"]
		 
	}
	
	/*
	def "Generate presentation from DocWithoutPluginStatements.adoc with default options"() {
		
		when: "DocWithoutPluginStatements.adoc content is loaded"
		
		def fileName = "DocWithoutPluginStatements"
		def fileExt = ".adoc"
		
		then: "generate a reveal and toto presentation."
		
		and : "Check if the generated file exists"
		Paths.get(name).toFile().exists() == true
		
		and : "Check if the generated file size is higher than zero byte"
		Paths.get(name).toFile().length() > 0

		where:
		name  << [fileName+".html", fileName+".html"]
		
	}
	
	def "Generate document from DocWithoutPluginStatements.adoc with options"(){
		
	}
	
	def "Generate presentation from DocWithoutPluginStatements.adoc with options"(){
	
	}*/
	
	
}
