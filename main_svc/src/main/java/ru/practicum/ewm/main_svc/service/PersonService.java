package ru.practicum.ewm.main_svc.service;

import ru.practicum.ewm.main_svc.model.dto.req.PersonCreateReq;
import ru.practicum.ewm.main_svc.model.dto.req.PersonUpdateReq;
import ru.practicum.ewm.main_svc.model.entity.Person;

public interface PersonService {
    Person create(PersonCreateReq req);

    Person update(PersonUpdateReq req);

    void delete(Long id);
}
