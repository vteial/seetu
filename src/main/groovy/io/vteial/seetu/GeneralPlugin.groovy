package io.vteial.seetu

import groovyx.gaelyk.plugins.PluginBaseScript
import io.vteial.seetu.service.impl.DefaultAccountService
import io.vteial.seetu.service.impl.DefaultAutoNumberService
import io.vteial.seetu.service.impl.DefaultCustomerService
import io.vteial.seetu.service.impl.DefaultItemService
import io.vteial.seetu.service.impl.DefaultUserService

import com.fasterxml.jackson.databind.ObjectMapper

class GeneralPlugin extends PluginBaseScript {

	@Override
	public Object run() {
		log.info "Registering GeneralPlugin started..."

		ObjectMapper jom = new ObjectMapper()

		DefaultAutoNumberService anS = new DefaultAutoNumberService()

		DefaultAccountService aS = new DefaultAccountService()
		aS.autoNumberService = anS

		DefaultUserService uS = new DefaultUserService()
		uS.autoNumberService = anS
		uS.accountService = aS

		DefaultCustomerService cS = new DefaultCustomerService()
		cS.autoNumberService = anS
		cS.accountService = aS

		DefaultItemService iS = new DefaultItemService()
		iS.autoNumberService = anS
		iS.accountService = aS

		binding {
			jsonObjectMapper  = jom
			autoNumberService = anS
			accountService    = aS
			userService       = uS
			customerService   = cS
			itemService       = iS
		}

		routes {
		}

		before {
			//log.info "Visiting ${request.requestURI}"
			//binding.uri = request.requestURI
		}

		after {
			//log.info "Exiting ${request.requestURI}"
			//log.info "groovlet returned $result from its exection"
		}

		log.info "Registering GeneralPlugin finished..."
	}
}
