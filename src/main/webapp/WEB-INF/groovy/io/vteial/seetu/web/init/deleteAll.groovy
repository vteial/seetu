package io.vteial.seetu.web.init;

import io.vteial.seetu.model.User

def entities = User.findAll()
entities.each { entity ->
	entity.delete()
}
println entities.size() + ' users deleted'

//entities = Item.findAll()
//entities.each { entity ->
//	entity.delete()
//}
//println entities.size() + ' items deleted'

println 'Deleted all...'