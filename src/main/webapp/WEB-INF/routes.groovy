email						to : '/receiveEmail.groovy'

jabber	chat,	 			to : '/receiveJabberMessage.groovy'
jabber 	presence,			to : '/receiveJabberpresence.groovy'
jabber	subscription, 		to : '/receiveJabberSubscription.groovy'

get 	'/favicon.ico',		redirect : '/assets/favicon.png'
get     '/',				forward  : '/index.gtpl'
get     '/index',			forward  : '/index.gtpl'
get 	'/info',			forward  : '/info.groovy'
//get	'/json',			forward  : '/json.groovy'
all 	'/_ah/warmup',		forward : '/ping.groovy'

// cron
get '/cron/dailyBackup',	forward : '/cron/dailyBackup.groovy'

// data
get '/init/reset',	    	forward : '/io/vteial/seetu/web/init/reset.groovy'
get '/init/deleteAll',   	forward : '/io/vteial/seetu/web/init/deleteAll.groovy'

// security
get  '/sessions/t',         forward : '/io/vteial/seetu/web/session/t.groovy'
post '/sessions/login',     forward : '/io/vteial/seetu/web/session/login.groovy'
get  '/sessions/logout',    forward : '/io/vteial/seetu/web/session/logout.groovy'

// item
//get    '/items',          forward : '/io/vteial/seetu/web/item/itemList.groovy'
//get    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemFindById.groovy?id=@id'
//post   '/items/item',     forward : '/io/vteial/seetu/web/item/itemCreate.groovy'
//put    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemUpdate.groovy?id=@id'
//delete '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemDelete.groovy?id=@id'

// user
get      '/users',          forward : '/io/vteial/seetu/web/user/list.groovy'
//get    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemFindById.groovy?id=@id'
//post   '/items/item',     forward : '/io/vteial/seetu/web/item/itemCreate.groovy'
//put    '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemUpdate.groovy?id=@id'
//delete '/items/item/@id', forward : '/io/vteial/seetu/web/item/itemDelete.groovy?id=@id'
