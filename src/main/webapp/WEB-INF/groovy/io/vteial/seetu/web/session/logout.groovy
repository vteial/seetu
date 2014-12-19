package io.vteial.seetu.web.session;

import io.vteial.seetu.dto.ResponseDto

import com.fasterxml.jackson.databind.ObjectMapper

session.removeAttribute('user')

ResponseDto rm = new ResponseDto(message : 'Successfully logged out...')

response.contentType = 'application/json'
jsonObjectMapper.writeValue(out, rm)