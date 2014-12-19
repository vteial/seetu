package io.vteial.seetu.web.user

import io.vteial.seetu.model.User

List<User> users = User.findAll()

response.contentType = 'application/json'
jsonObjectMapper.writeValue(out, users)

