package io.vteial.seetu.web.session

import io.vteial.seetu.dto.ResponseDto
import io.vteial.seetu.model.User

response.contentType = 'application/json'

ResponseDto rm = new ResponseDto(message : 'Invalid User Id or Password')

User userA = jsonObjectMapper.readValue(request.reader.text, User.class);
User userE = User.get(userA.id)
if(!userE) {
	jsonObjectMapper.writeValue(out, rm)
	return
}
if(app.env.name == 'Production' && userE.password != userA.password) {
	jsonObjectMapper.writeValue(out, rm)
	return
}

session.user = userE

jsonObjectMapper.writeValue(out, userE)
