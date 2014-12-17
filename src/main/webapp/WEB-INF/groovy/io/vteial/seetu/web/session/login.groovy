package io.vteial.seetu.web.session

import io.vteial.seetu.dto.MessageDto
import io.vteial.seetu.model.User

import com.fasterxml.jackson.databind.ObjectMapper

response.contentType = 'application/json'
ObjectMapper mapper = new ObjectMapper();

MessageDto messageDto = new MessageDto(message : 'Invalid User Id or Password')

User userA = mapper.readValue(request.reader.text, User.class);
User userE = User.get(userA.id)
if(!userE) {
	mapper.writeValue(out, messageDto)
	return
}
if(app.env.name == 'Production' && userE.password != userA.password) {
	mapper.writeValue(out, messageDto)
	return
}

session.user = userE

mapper.writeValue(out, userE)
