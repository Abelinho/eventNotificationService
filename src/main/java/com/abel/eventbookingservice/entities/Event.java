package com.abel.eventbookingservice.entities;

import com.abel.eventbookingservice.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "`EVENT`")
public class Event extends AbstractEntity{

	@Column(nullable = false)
	//@Max(value = 100)
	private String eventName;

	@Column(name="event_date",nullable = false)//default nullable=true
	private LocalDateTime eventDate;

	@Column(name = "attendee_count", nullable = false)
	private int attendeeCount;

	@Column(name = "event_description",nullable = false)
	private String eventDescription;

	@Column(name = "category",nullable = false)
	@Builder.Default
	private String category = Category.Concert.code;

	@Column(name = "category",nullable = false)
	@Builder.Default
	private boolean booked = false;

//	@OneToMany(fetch = FetchType.LAZY,mappedBy ="event")
//	private Set<Ticket> ticket; //not neccessary in this scenario

}
