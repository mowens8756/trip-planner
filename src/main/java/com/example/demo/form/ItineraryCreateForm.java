package com.example.demo.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.model.Itinerary;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItineraryCreateForm implements Serializable{
	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Integer trip_id;
	@NotBlank
	@Size(max=30)
	private String username;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date itinerary_date;
	@DateTimeFormat(pattern="HH:mm")
	private Time start_at;
	@DateTimeFormat(pattern="HH:mm")
	private Time end_at;
	@NotBlank
	@Size(max=200)
	private String location;
	private String note;
	private BigDecimal amount;
	
	/**
     * Formクラスの設定内容を文字列で出力する.
     */
    @Override
    public String toString() {
        return "Itinerary(trip_id: " + this.getTrip_id() + ", username: " + this.getUsername()
        +", itinerary_date: " + this.getItinerary_date()
        +", start_at: " + this.getStart_at() +", end_at: " + this.getEnd_at()
        +", location: " + this.getLocation() +", note: " + this.getNote()
        + ", amount: " + this.getAmount() +")";
    }    
    /**
     * Formの設定内容をItinerary Entityクラスに変換する.
     *
      * @return 情報(Entityクラス)
     */
    public Itinerary toEntity(){

    	Itinerary itinerary = new Itinerary();
    	itinerary.setTrip_id(this.getTrip_id());
    	itinerary.setUsername(this.getUsername());
    	itinerary.setItinerary_date(this.getItinerary_date());
    	itinerary.setStart_at(this.getStart_at());
    	itinerary.setEnd_at(this.getEnd_at());
    	itinerary.setLocation(this.getLocation());
    	itinerary.setNote(this.getNote());
    	itinerary.setAmount(this.getAmount());
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        itinerary.setCreated_at(current_time);
        itinerary.setUpdated_at(current_time);
        return itinerary;
    }
}
