package io.vteial.seetu.web.session;

import io.vteial.seetu.dto.MessageDto

import com.fasterxml.jackson.databind.ObjectMapper

session.removeAttribute('user')

response.contentType = 'application/json'
ObjectMapper mapper = new ObjectMapper();
MessageDto messageDto = new MessageDto(message : 'Successfully logged out...')
mapper.writeValue(out, messageDto)