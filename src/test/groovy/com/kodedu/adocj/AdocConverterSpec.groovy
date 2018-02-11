package com.kodedu.adocj

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

	def "generate document from correct asciidoc content"() {
		expect:
		name.size() == length

		where:
		name     | length
		"Spock"  | 5
		"Kirk"   | 4
		"Scotty" | 6
	}
	
	def "throw Exception when content is bad"(){
		
	}
	
	
}
