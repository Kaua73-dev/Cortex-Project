package com.api.cortex.service.event;


import com.api.cortex.auth.AuthVerifyService;
import com.api.cortex.model.dto.request.event.EventRequest;
import com.api.cortex.model.dto.response.event.EventResponse;
import com.api.cortex.model.entity.event.Event;
import com.api.cortex.model.entity.user.User;
import com.api.cortex.model.repository.event.EventRepository;
import com.api.cortex.service.email.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final AuthVerifyService authVerifyService;
    private final EmailService emailService;


    public EventService(EventRepository eventRepository, AuthVerifyService authVerifyService, EmailService emailService) {
        this.eventRepository = eventRepository;
        this.authVerifyService = authVerifyService;
        this.emailService = emailService;
    }


    public EventResponse createEvent(EventRequest request){
        User user = authVerifyService.getAuthenticate();

        Event event = new Event();
        event.setEventText(request.eventText());
        event.setCreateAt(LocalDateTime.now());
        event.setUser(user);

        Event eventSaved = eventRepository.save(event);
        emailService.emailEventCreated(user.getEmail());

        return new EventResponse(eventSaved.getEventText());

    }





}
