package com.abel.eventbookingservice.jobs;

import com.abel.eventbookingservice.entities.AuditLog;
import com.abel.eventbookingservice.entities.Ticket;
import com.abel.eventbookingservice.repos.AuditRepository;
import com.abel.eventbookingservice.repos.TicketRepository;
import com.abel.eventbookingservice.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Configuration
@RequiredArgsConstructor
public class EventNotificationJob {

private final TicketRepository ticketRepository;

private final AuditRepository auditRepository;

private final EmailService emailService;

@Scheduled(cron = "${customer.notification.expression}")
public void notifyCustomer(){

    log.info(":::: STARTING CUSTOMER NOTIFICATION JOB ::::");

    //to do: fetch all booked tickets(with future dates)
    // get their event id and send a dummy notification(due to unclear requirements!)
    //create audit log

   List<Ticket> reservationList = ticketRepository.findAll();

   reservationList.forEach((ticket)->{
      Long id = ticket.getEvent().getId();
      LocalDateTime eventDate = ticket.getEvent().getEventDate();
      String attendeeEmail = ticket.getAttendeeEmail();
      sendNotification(id,attendeeEmail,eventDate);
      createAuditLog(id);
   });



}

    private void createAuditLog(Long id) {

    log.info("creating audit log for event with id: "+ id);

        AuditLog audit = new AuditLog();
        audit.setEventDate(new Date());
        audit.setEventId(id);
        auditRepository.save(audit);

    }

    private void sendNotification(Long id, String email,LocalDateTime eventdate) {

    log.info("sending a mail to customer with email: "+email+" for event with id: "+id);
    emailService.sendEmail(email,"notification for upcoming event",
            "you have an upcoming event on: "+eventdate);


    }

}
