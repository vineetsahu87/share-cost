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
 * The Service handles the Event and Ledger services.
 * 
 * @author Vineet Sahu (vineetsahu87@gmail.com)
 */
@Path("/event")
public class EventService {

	/**
	 * Adds a new event. Method handling HTTP POST requests. The returned object
	 * will be sent to the client as "application/json" media type.
	 *
	 * @param event - Event to be added.
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
	 * @param eventId - event Id for which the event needs to be retrieved.
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
	 * @param userId - userID for which the events needs to be retrieved.
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
	 * Adds the expense for the event. Method handling HTTP POST requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 *
	 * @param eventId - event Id for which the expense needs to be added
	 * @param expense - expense to be added for the event.
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
	 * @param eventId - event Id for which the expenses needs to be retrieved.
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
	 * Retrieves the list of expenses for an event done by a user.Method handling
	 * HTTP GET requests. The returned object will be sent to the client as
	 * "application/json" media type.
	 *
	 * @param eventId - event ID for which the expenses needs to be retrieved.
	 * @param userId - user Id who has done the expense for the event.
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
	 * Adds a ledger for an expense. Method handling HTTP POST requests. The
	 * returned object will be sent to the client as "application/json" media type.
	 *
	 * @param expenseId - expense Id for which the ledger needs to be added.
	 * @param ledger - Ledger entry to be added for the expense.
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
	 * The method returns the amount owed by userB to userA. Method handling HTTP
	 * GET requests. The returned object will be sent to the client as
	 * "application/json" media type.
	 *
	 * @param userA - userId for the user who has lend money to userB.
	 * @param userB - userId for the user who owes money to userA.
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
	 * Retrieve cost of the event for the user. Method handling HTTP GET requests.
	 * The returned object will be sent to the client as "application/json" media
	 * type.
	 *
	 * @param - eventId - event Id for which the cost needs to be retrieved.
	 * @param - userId - user Id for which the cost needs to be retrieved for the
	 *          event.
	 * 
	 * @return String that will be returned as a application/json response.
	 */
	@GET
	@Path("/{eventId}/user/{userId}/cost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventCostForUser(@PathParam(value = "eventId") String eventId,
			@PathParam(value = "userId") String userId) {
		EventLedgerService service = EventLedgerFactory.getEventLedgerImpl();
		double cost = service.getEventCostForUser(eventId, userId);
		return Response.status(Status.OK).entity(cost).build();
	}
}
