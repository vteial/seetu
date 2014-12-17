package io.vteial.seetu.web.user

import io.vteial.seetu.model.User

import com.fasterxml.jackson.databind.ObjectMapper

ObjectMapper mapper = new ObjectMapper()

List<User> users = User.findAll()

response.contentType = 'application/json'
mapper.writeValue(out, users)

