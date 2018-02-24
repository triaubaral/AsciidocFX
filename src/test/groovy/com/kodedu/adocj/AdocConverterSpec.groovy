package com.kodedu.adocj

import java.nio.file.FileVisitResult
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.BasicFileAttributes

import com.kodedu.service.converter.AdocConverter

import spock.lang.Specification
import spock.lang.Unroll

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

@Unroll
class AdocConverterSpec extends Specification{

	def File workingDir
	def InputStream adocFileBackup
	def File adocFileToProcess
	
	def setup() {

		String adocFileName = "DocWithoutPluginStatements.adoc"
		
		workingDir = Files.createTempDirectory("tmp").toFile()
			
		adocFileBackup = this.getClass().getResourceAsStream("/"+adocFileName)
		adocFileToProcess = new File(workingDir.getPath()+File.separator+adocFileName)
		
		if(!adocFileToProcess.exists()) {
			adocFileToProcess.createNewFile()
		}
				
		OutputStream tmp = new FileOutputStream(adocFileToProcess)
		
		tmp << adocFileBackup
		adocFileBackup.close()
		tmp.close()

	}

	def cleanup() {

		Files.walkFileTree(Paths.get(workingDir.getAbsolutePath()), new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						Files.delete(file);
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					}
				});
	}

	def "Generate #outputFileName from DocWithoutPluginStatements.adoc file with default options"() {

		given: "DocWithoutPluginStatements.adoc file is loaded"

		AdocConverter converter = AdocConverter.Factory.create(AdocConverter.Backend.valueOf(backend))

		when: "generate a #backend file."

		String strResult = converter.convertFile(adocFileToProcess)

		then : "Check if the generated file exists"
		File result = Paths.get(workingDir.getAbsolutePath()+File.separator+outputFileName).toFile()
		result.exists() == true

		and : "Check if the generated file size is higher than zero byte"
		result.text.length() > 0

		where:
		outputFileName                       | backend   
		"DocWithoutPluginStatements.pdf"     |  "PDF"
		"DocWithoutPluginStatements.html"    |  "HTML"
		"DocWithoutPluginStatements.xml"     |  "DOCBOOK"
		//"DocWithoutPluginStatements.epub"    |  "EPUB3"
		
		//name  << ["DocWithoutPluginStatements.pdf", "DocWithoutPluginStatements.html", "DocWithoutPluginStatements.ebook"]

	}


	

	def "Generate #outputFileName from DocWithoutPluginStatements.adoc content with default options"() {

		given: "DocWithoutPluginStatements.adoc content is loaded"

		AdocConverter converter = AdocConverter.Factory.create(AdocConverter.Backend.valueOf(backend))

		String adocContent = adocFileToProcess.getText()

		when: "generate a #backend file."

		File output = new File(adocFileToProcess.getParent()+File.separator+outputFileName);

		String strResult = converter.convertContentToFile(adocContent, output)

		then : "Check if the generated file exists"
		File result = Paths.get(workingDir.getAbsolutePath()+File.separator+outputFileName).toFile()
		result.exists() == true

		and : "Check if the generated file size is higher than zero byte"
		result.text.length() > 0
		
		where:
		outputFileName | backend   
		"toto.pdf"     |  "PDF"
		"toto.html"    |  "HTML"
		"toto.xml"     |  "DOCBOOK"
		//"toto.epub"    |  "EPUB3"
	
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
