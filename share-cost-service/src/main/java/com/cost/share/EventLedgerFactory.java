package com.cost.share;

import java.util.ServiceLoader;

import com.cost.share.service.EventLedgerService;

/**
 * Event Factory
 *
 */
public class EventLedgerFactory {

	public static EventLedgerService getEventLedgerImpl() {
		 ServiceLoader<EventLedgerService> serviceLoader =
	              ServiceLoader.load(EventLedgerService.class);
		 return (EventLedgerService) serviceLoader.iterator().next();
	}
}
