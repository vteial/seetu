def props             = app.clone()
props.applicationUser = user
props.sessionUser     = session.user
props.sessionId       = session.id
props.localMode       = localMode

response.contentType = 'application/json'
jsonObjectMapper.writeValue(out, props)