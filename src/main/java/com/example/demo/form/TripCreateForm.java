package com.example.demo.form;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.model.Trip;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TripCreateForm implements Serializable{

	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(max=30)
	private String username;
	@NotBlank
	@Size(max=255)
	private String title;
	@NotBlank
	@Size(max=100)
	private String destination;
	@NotNull
	private Double travel_days;
	private int currency;
	
	/**
     * Formクラスの設定内容を文字列で出力する.
     */
    @Override
    public String toString() {
        return "Trip(username: " + this.getUsername() + ", title: " + this.getTitle()
        +", destination: " + this.getDestination() +", travel_days: " + this.getTravel_days()
        +", currency: " + this.getCurrency()  +")";
    }
    /**
     * Formの設定内容をTrip Entityクラスに変換する.
     *
      * @return 情報(Entityクラス)
     */
    public Trip toEntity(){

        Trip trip = new Trip();
        trip.setUsername(this.getUsername());
        trip.setTitle(this.getTitle());
        trip.setDestination(this.getDestination());
        trip.setTravel_days(this.getTravel_days());
        trip.setCurrency(this.getCurrency());
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        trip.setCreated_at(current_time);
        trip.setUpdated_at(current_time);
        return trip;
    }
}
