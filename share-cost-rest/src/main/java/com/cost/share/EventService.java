package com.cost.share;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cost.share.model.Event;
import com.cost.share.model.Expense;
import com.cost.share.model.Ledger;
import com.cost.share.service.EventLedgerService;

/**
 * @author Vineet Sahu
 */
@Path("/event")
public class EventService {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvent(Event event) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		service.addEvent(event);
		return Response.status(Status.OK).build();
	}

	/**
	 * Method handling HTTP GET requests to retrieve the event given the eventId.
	 * The returned object will be sent to the client as "application/json" media
	 * type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvent(@PathParam(value = "eventId") String eventId) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		Event event = service.getEvent(eventId);
		return Response.status(Status.OK).entity(event).build();
	}

	/**
	 * Method handling HTTP GET requests to retrieve the list of event user is part
	 * of. The returned object will be sent to the client as "application/json"
	 * media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventsForUser(@PathParam(value = "userId") String userId) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		List<Event> events = service.getEvents(userId);
		return Response.status(Status.OK).entity(events).build();
	}

	/**
	 * Method handling HTTP POST requests to add expense for and event. The returned
	 * object will be sent to the client as "application/json" media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@POST
	@Path("/{eventId}/expense")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExpense(@PathParam(value = "eventId") String eventId, Expense expense) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		service.addExpense(eventId, expense);
		return Response.status(Status.OK).build();
	}

	/**
	 * Method handling HTTP GET requests to retrieve the list of expenses for an
	 * event. The returned object will be sent to the client as "application/json"
	 * media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/{eventId}/expense")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventTotalExpense(@PathParam(value = "eventId") String eventId) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		List<Expense> expenses = service.getEventExpense(eventId);
		return Response.status(Status.OK).entity(expenses).build();
	}

	/**
	 * Method handling HTTP GET requests to retrieve the list of expenses for an
	 * event. The returned object will be sent to the client as "application/json"
	 * media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/{eventId}/expense/user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventExpense(@PathParam(value = "eventId") String eventId,
			@PathParam(value = "userId") String userId) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		List<Expense> expenses = service.getEventExpenseForUser(eventId, userId);
		return Response.status(Status.OK).entity(expenses).build();
	}

	/**
	 * Method handling HTTP POST requests to add expense for and event. The returned
	 * object will be sent to the client as "application/json" media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@POST
	@Path("/{eventId}/expense/{expenseId}/ledger")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLedger(@PathParam(value = "expenseId") String expenseId, Ledger ledger) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		service.addLedger(expenseId, ledger);
		return Response.status(Status.OK).build();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "application/json" media type. The method returns the amount owed
	 * by userB to userA
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoanAmount(@QueryParam(value = "userA") String userA,
			@QueryParam(value = "userB") String userB) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		double amountOwed = service.getLoanAmount(userA, userB);
		return Response.status(Status.OK).entity(amountOwed).build();
	}

	/**
	 * Method handling HTTP GET requests to retrieve cost for the event for the
	 * user. The returned object will be sent to the client as "application/json"
	 * media type.
	 *
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("{eventId}/user/{userId}/cost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventCos(@PathParam(value = "eventId") String eventId,
			@PathParam(value = "userId") String userId) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		double cost = service.getEventCostForUser(eventId, userId);
		return Response.status(Status.OK).entity(cost).build();
	}
}
