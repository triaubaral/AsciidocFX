package com.kodedu.adocj

import java.nio.file.Paths

import com.kodedu.service.converter.AdocConverter

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

	def "Generate document from DocWithoutPluginStatements.adoc content with default options"() {
		
		when: "DocWithoutPluginStatements.adoc content is loaded"
		
		def fileName = "DocWithoutPluginStatements"
		def fileExt = ".adoc"
		
		String adocContent = new File("src/test/resources/DocWithoutPluginStatements.adoc").text;		
		
		then: "generate a pdf file."
		
		AdocConverter converter = AdocConverter.Factory.create();
		
		converter.convert(adocContent, AdocConverter.OptionsBuilder.options().basedir("target").toFile(new File("DocWithoutPluginStatements.pdf")).backend("pdf"));
		
		and : "Check if the generated file exists"
		Paths.get("target/"+name).toFile().exists() == true
		
		and : "Check if the generated file size is higher than zero byte"
		Paths.get("target/"+name).toFile().length() > 0

		where:
		name  = "DocWithoutPluginStatements.pdf";
		//name  << ["DocWithoutPluginStatements.pdf", "DocWithoutPluginStatements.html", "DocWithoutPluginStatements.ebook"]                            
		 
	}
	
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
	
	}
	
	
}
