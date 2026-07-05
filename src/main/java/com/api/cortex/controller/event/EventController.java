package com.api.cortex.controller.event;


import com.api.cortex.model.dto.request.event.EventRequest;
import com.api.cortex.model.dto.response.event.EventResponse;
import com.api.cortex.service.event.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;


    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public EventResponse createEvent(@RequestBody EventRequest request){
        return eventService.createEvent(request);
    }


}
