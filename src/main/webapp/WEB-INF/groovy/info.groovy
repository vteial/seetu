import com.google.apphosting.api.ApiProxy
import com.google.apphosting.api.ApiProxy.Environment

println "App Info = ${app}"
println '-----------------------------------------------------'
println "Request URI = ${request.requestURI}"
println "Request URL = ${request.requestURL}"
headers.each { header -> println "${header.key} = ${header.value}" }
println '-----------------------------------------------------'
Environment env = ApiProxy.getCurrentEnvironment();
env.getAttributes().each { attr -> println "${attr.key} = ${attr.value}" }
println '-----------------------------------------------------'

println app.env