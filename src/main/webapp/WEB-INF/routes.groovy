email	to : '/receiveEmail.groovy'

jabber	chat,	 		to : '/receiveJabberMessage.groovy'
jabber 	presence,		to : '/receiveJabberpresence.groovy'
jabber	subscription, 	to : '/receiveJabberSubscription.groovy'

get 	'/info',		forward  : '/info.groovy'
get 	'/favicon.ico',	redirect : '/assets/favicon.png'

all 	'/_ah/warmup',	forward : '/ping.groovy'

// cron
get '/cron/dailyBackup',	forward : '/cron/dailyBackup.groovy'
// data
get '/init/createAll',	    forward : '/io/vteial/seetu/web/init/createAll.groovy'
get '/init/deleteAll',   	forward : '/io/vteial/seetu/web/init/deleteAll.groovy'
get '/init/createUsers', 	forward : '/io/vteial/seetu/web/init/createUsers.groovy'
//get '/init/createItems', 	forward : '/io/vteial/seetu/web/init/createItems.groovy'

// security
get  '/sessions/t',         forward : '/io/vteial/seetu/web/session/t.groovy'
post '/sessions/login',     forward : '/io/vteial/seetu/web/session/login.groovy'
post '/sessions/logout',    forward : '/io/vteial/seetu/web/session/logout.groovy'

// item
//get    '/items',          forward : '/io/vteial/seetu/web/item/itemList.groovy'
//get    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemFindById.groovy?id=@id'
//post   '/items/item',     forward : '/io/vteial/seetu/web/item/itemCreate.groovy'
//put    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemUpdate.groovy?id=@id'
//delete '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemDelete.groovy?id=@id'

// item
get    '/users',          forward : '/io/vteial/seetu/web/user/userList.groovy'
//get    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemFindById.groovy?id=@id'
//post   '/items/item',     forward : '/io/vteial/seetu/web/item/itemCreate.groovy'
//put    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemUpdate.groovy?id=@id'
//delete '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemDelete.groovy?id=@id'
