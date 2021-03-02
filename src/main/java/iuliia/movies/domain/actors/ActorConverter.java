package iuliia.movies.domain.actors;

import movies.iuliia.service.actors.Actor;
import movies.iuliia.service.actors.ActorInvoice;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class ActorConverter {

    public Actor toActor(ActorEntity entity) {
        var actor = new Actor();
        actor.setId(entity.getId());
        actor.setFirstName(entity.getFirstName());
        actor.setLastName(entity.getLastName());
        actor.setMiddleName(entity.getMiddleName());
        var birthday = entity.getBirthday();
        if (birthday != null) {
            try {
                var xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                xmlGregorianCalendar.setYear(birthday.getYear());
                xmlGregorianCalendar.setMonth(birthday.getMonthValue());
                xmlGregorianCalendar.setDay(birthday.getDayOfMonth());
                actor.setBirthday(xmlGregorianCalendar);
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
        }
        return actor;
    }

    public ActorEntity toEntity(ActorInvoice invoice) {
        var entity = new ActorEntity();
        entity.setFirstName(invoice.getFirstName());
        entity.setLastName(invoice.getLastName());
        entity.setMiddleName(invoice.getMiddleName());
        var birthday = invoice.getBirthday();
        if (birthday != null) {
            var date = LocalDateTime.parse(birthday.toString())
                    .atOffset(OffsetDateTime.now().getOffset()).toLocalDate();
            entity.setBirthday(date);
        }
        return entity;
    }

    public ActorEntity toEntity(Actor actor) {
        var entity = new ActorEntity();
        entity.setId(actor.getId());
        entity.setFirstName(actor.getFirstName());
        entity.setLastName(actor.getLastName());
        entity.setMiddleName(actor.getMiddleName());
        var birthday = actor.getBirthday();
        if (birthday != null) {
            var date = LocalDateTime.parse(birthday.toString())
                    .atOffset(OffsetDateTime.now().getOffset()).toLocalDate();
            entity.setBirthday(date);
        }
        return entity;
    }
}
