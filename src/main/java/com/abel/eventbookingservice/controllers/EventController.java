package com.abel.eventbookingservice.controllers;

import com.abel.eventbookingservice.dto.EventRequestDTO;
import com.abel.eventbookingservice.dto.EventResponseDTO;
import com.abel.eventbookingservice.dto.TicketRequest;
import com.abel.eventbookingservice.entities.Event;
import com.abel.eventbookingservice.services.EventService;
//import jakarta.validation.Valid;
//import javax.validation.Valid;
import com.abel.eventbookingservice.services.TicketService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;
	private final TicketService ticketService;

	@GetMapping(value = "/events")//test this endpoint by preloading event table: use data.sql or command line runner
	public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(required = false) String name,
						  @RequestParam(required = false) String startDate,
						  @RequestParam(required = false) String endDate,
						  @RequestParam(required = false) String category){

		LocalDateTime start = LocalDateTime.now();
		LocalDateTime end = LocalDateTime.of(2030,12,30,23,30);
		//parse the date String to a LocalDateTime
		if (Objects.nonNull(startDate) && Objects.nonNull(endDate)){
			//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			start =  LocalDateTime.parse(startDate);//,formatter
			 end = LocalDateTime.parse(endDate);//,formatter
		}


		List<EventResponseDTO> events = eventService.getEvents(name,start,end,category);

		if (events.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//		//build the response and return
//		EventResponseDTO eventResponseDTO = EventResponseDTO.builder()
//				                           .availableAttendeesCount()
		  return new ResponseEntity<>(eventService.getEvents(name,start,end,category), HttpStatus.OK) ;

		//return null;//"this returns get all events";
	}

	@PostMapping(value = "/events/{eventId}/tickets")
	public ResponseEntity<String> reserveTicket(@RequestBody @Valid TicketRequest ticketRequest, @PathVariable Long eventId){

		Long reservedTicketEventid = ticketService.reserveTicket(ticketRequest,eventId);

       return new ResponseEntity<String>("eventId: "+String.valueOf(reservedTicketEventid),HttpStatus.CREATED);

    //To do:
		//..see vids on users and roles in jwt
		// 1. add endpoint for viewing booked events(booked event has 'booked'flag=true)
		// ...admin user can see all booked events while ordinary users can see only events the booked
		// 2. add endpoint for cancel reserved tickets(for logged in user i.e search by userid).
		// Can all users see all booked events/only event they booked?
		// (ticket has 'cancelled' flag=true/soft delete)
		//..reserved ticket shd have id of logged in user/user that reserved it(one-to-one relationship
		//...between ticket and logged in user
        //shd 'cancel reservation endpoint delete the saved ticket?' no most likely
		//5. implement user registration login via jwt
		//3. implement a job that notifies users of upcoming 'booked events'(add a flag to event to mark it
		// as booked/not booked) and create history/audit event log
		//4. write unit tests for controller and service methods


//       ## Requirements
//
//		While implementing your solution **please take care of the following requirements:**
//
//### Functional requirements
//
//		- The REST API methods should be implemented based on the specification provided in the linked swagger file;
//		- Add 2 new methods, one to **view** your booked events and one to **cancel** your reservation _**(both should be authorized)**_;
//		- Introduce a periodic task to send notifications for upcoming events to users and create history/audit event log for this.
//				- No need for UI;
//
//### Non-functional requirements
//
//				- The project MUST be buildable and runnable;
//		- The project MUST have Unit tests;
//		- The project MUST have a README file with build/run/test instructions (use a DB that can be run locally, e.g. in-memory, via container);
//		- Any data required by the application to run (e.g. reference tables, dummy data) MUST be preloaded in the database;
//		- Input/output data MUST be in JSON format;
//		- Use a framework of your choice, but popular, up-to-date, and long-term support versions are recommended.
//
//		---




//		/events/{eventId}/tickets:
//		post:
//		summary: Reserve tickets for an event.
//		description: This endpoint allows customers to reserve tickets for an event.
//		parameters:
//		- in: path
//		name: eventId
//		description: The ID of the event to reserve tickets for.
//		required: true
//		type: integer
//		format: int64
//				- in: body
//		name: ticketRequest
//		description: The ticket reservation request.
//		schema:
//		$ref: '#/definitions/TicketRequest'
//		responses:
//		'201':
//		description: Created
//		'400':
//		description: Bad request
//		'401':
//		description: Unauthorized
//		tags:
//		- tickets
//		security:
//		- Bearer: []
	}

	@PostMapping(value = "/events")
    public ResponseEntity<EventResponseDTO> createEvent(@Valid  @RequestBody EventRequestDTO eventRequestDTO){

      //call eventService to create the event and get the response
      log.info("creating an event....");
      EventResponseDTO response =  eventService.createEvent(eventRequestDTO);

    	return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));


//    	/events:
//		post:
//		summary: Create a new event.
//				description: This endpoint allows customers to create a new event.
//				parameters:
//		- in: body
//		name: event
//		description: The event to create.
//		schema:
//		$ref: '#/definitions/EventRequestDTO'
//		responses:
//		'201':
//		description: Created
//		schema:
//		type: object
//		properties:
//		eventId:
//		type: integer
//		format: int64
//		'400':
//		description: Bad request
//		'401':
//		description: Unauthorized
//		tags:
//		- events
//		security:
//		- Bearer: []

	}


//	@PostMapping(value = "/notification", produces = "Application/json",
//			consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
//	public ResponseEntity<AppResponse<NotificationResponse>>
//	sendNotification( NotificationRequest notificationRequest,
//					  @Nullable List<MultipartFile> attachments)  {
//		log.info("Response Body: {}", new Gson().toJson(notificationRequest));
//
////        //call the service
//		NotificationResponse notificationResponse = notificationService.sendNotification(notificationRequest, attachments);
//
//		AppResponse<NotificationResponse> response = AppResponse.<NotificationResponse>builder()
//				.message("Successfully processed. Your can use the provided id to check notification status")
//				.status(HttpStatus.CREATED.toString())
//				.data(notificationResponse)
//				.build();
//		return ResponseEntity.ok().body(response);
//	}


//	@RequestMapping("findFlights")
//	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
//			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
//			ModelMap modelMap) {
//
//		LOGGER.info("Inside findFlights() From:" + from + " TO:" + to + "Departure Date: " + departureDate);
//		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
//		modelMap.addAttribute("flights", flights);
//		LOGGER.info("Flight Found are:" + flights);
//		return "displayFlights";
//
//	}
	
//	@RequestMapping("admin/showAddFlight")
//	public String showAddFlight() {
//		return "addFlight";
//	}

}
